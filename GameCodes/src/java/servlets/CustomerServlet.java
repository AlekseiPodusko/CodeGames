
package servlets;

import entity.History;
import entity.Product;
import entity.User;
import entity.Code;
import session.HistoryFacade;
import session.ProductFacade;
import session.UserFacade;
import session.UserRolesFacade;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.CodeFacade;
import tools.CodeProtector;
import tools.PasswordProtector;

@WebServlet(name = "CustomerServlet", urlPatterns = {
    "/showEditUser",
    "/editUser",
    "/showAddMoney",
    "/addMoney",
    "/showBuyProduct",
    "/buyProduct",
    "/showMyPurchases"
})
public class CustomerServlet extends HttpServlet {
    @EJB private UserRolesFacade userRolesFacade;
    @EJB private UserFacade userFacade;
    @EJB private ProductFacade productFacade;
    @EJB private HistoryFacade historyFacade;
    @EJB private CodeFacade codeFacade;
    static enum Role {CUSTOMER};

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        if(session == null){
            request.setAttribute("info", "Авторизуйтесь!");
            request.getRequestDispatcher("/showLogin").forward(request, response);
        }
        User authUser = (User) session.getAttribute("authUser");
        if(authUser == null){
            request.setAttribute("info", "Авторизуйтесь!");
            request.getRequestDispatcher("/showLogin").forward(request, response);
        }
        if(!userRolesFacade.isRole("CUSTOMER", authUser)){
            request.setAttribute("info", "У вас нет прав!");
            request.getRequestDispatcher("/showLogin").forward(request, response);
        }
        session.setAttribute("topRole", userRolesFacade.getTopRole(authUser));
        
        String path = request.getServletPath();
        switch(path) {
            
            case "/showEditUser":
                request.setAttribute("firstName", authUser.getFirstName());
                request.setAttribute("sureName", authUser.getSureName());
                request.setAttribute("email", authUser.getEmail());
                request.setAttribute("login", authUser.getLogin());
                request.getRequestDispatcher("/WEB-INF/customers/showEditUser.jsp").forward(request, response);
                break;
                
            case "/editUser":
                boolean changePassword = false;
                String firstName = request.getParameter("firstName");
                String sureName= request.getParameter("sureName");
                String email = request.getParameter("email");
                String login = request.getParameter("login");
                String oldPassword = request.getParameter("oldPassword");
                String newPassword1 = request.getParameter("newPassword1");
                String newPassword2 = request.getParameter("newPassword2");
                
                if (firstName.isEmpty() || sureName.isEmpty() || email.isEmpty() || login.isEmpty()) {
                    request.setAttribute("firstName", firstName);
                    request.setAttribute("sureName", sureName);
                    request.setAttribute("email", email);
                    request.setAttribute("login", login);
                    request.setAttribute("info", "Заполните все поля!");
                    request.getRequestDispatcher("/WEB-INF/customers/showEditUser.jsp").forward(request, response);
                }
                PasswordProtector passwordProtector = new PasswordProtector();
                if (!oldPassword.isEmpty()) {
                    String password = passwordProtector.getProtectedPassword(oldPassword, authUser.getSalt());
                    if (!password.equals(authUser.getPassword())) {
                        request.setAttribute("info", "Неверный пароль");
                        request.getRequestDispatcher("/showEditUser").forward(request, response);
                        break;
                    }
                    if ("".equals(newPassword1) || "".equals(newPassword2)) {
                        request.setAttribute("info", "Заполните поля паролей");
                        request.getRequestDispatcher("/showEditUser").forward(request, response);
                        break;
                    }
                    if (!newPassword1.equals(newPassword2)) {
                        request.setAttribute("info", "Новые пароли не совпадают");
                        request.getRequestDispatcher("/showEditUser").forward(request, response);
                        break;
                    }
                    if (newPassword1.equals(oldPassword)) {
                        request.setAttribute("info", "Новый пароль не может совпадать со старым");
                        request.getRequestDispatcher("/showEditUser").forward(request, response);
                        break;
                    }
                    changePassword = true;
                    authUser.setPassword(passwordProtector.getProtectedPassword(newPassword1, authUser.getSalt()));
                }
                
                authUser.setFirstName(firstName);
                authUser.setSureName(sureName);
                authUser.setEmail(email);
                authUser.setLogin(login);
                userFacade.edit(authUser);
                if (changePassword) {
                    session.setAttribute("changePassword", "true");
                    request.getRequestDispatcher("/logout").forward(request, response);
                    break;
                }
                request.setAttribute("changePassword", "false");
                request.setAttribute("info", "Данные успешно обновлены");
                request.getRequestDispatcher("/showEditUser").forward(request, response);
                break;
                
            case "/showAddMoney":
                request.getRequestDispatcher("/WEB-INF/purchases/addMoney.jsp").forward(request, response);
                break;
                
            case "/addMoney":
                String moneyToAdd = request.getParameter("money");
                authUser.setCash(authUser.getCash() + Double.parseDouble(moneyToAdd));
                userFacade.edit(authUser);
                request.setAttribute("info", "Счет успешно пополнен");
                request.getRequestDispatcher("/showAddMoney").forward(request, response);
                break;
                
            case "/showBuyProduct":
                String productId = request.getParameter("id");
                Product product = productFacade.find(Long.parseLong(productId));
                request.setAttribute("product", product);
                request.setAttribute("cash", authUser.getCash());
                request.getRequestDispatcher("/WEB-INF/purchases/buyProduct.jsp").forward(request, response);
                break;
                
            case "/buyProduct":
                History history = new History();
                productId = request.getParameter("id");
                product = productFacade.find(Long.parseLong(productId));
                Code code = codeFacade.findFreeCodeByProduct(product);

                if (code == null) {
                    request.setAttribute("info", "Нет доступных кодов для этого продукта");
                    request.getRequestDispatcher("/listProducts").forward(request, response);
                    return;
                }

                if (authUser.getCash() >= product.getPrice()) {
                    // 💰 Снимаем деньги и создаём запись
                    history.setProduct(product);
                    history.setCode(code);
                    history.setUser(authUser);
                    history.setPurchaseDate(localdateToDate(LocalDate.now()));
                    historyFacade.edit(history);

                    // 💾 Обновляем состояние кода
                    code.setPurchase("yes");
                    codeFacade.edit(code);

                    // Расшифровываем код перед записью в историю
                    CodeProtector protector = new CodeProtector();
                    String decryptedCode = protector.decrypt(code.getCode(), code.getSalt());

                    // Присваиваем расшифрованный код в историю
                    history.getCode().setCode(decryptedCode);  // Здесь расшифрованный код сохраняется в истории

                    // Выводим расшифрованный код в консоль для проверки
                    System.out.println("Расшифрованный код: " + decryptedCode);

                    // 💸 Обновляем деньги у пользователя
                    authUser.setCash(authUser.getCash() - product.getPrice());
                    userFacade.edit(authUser);

                    // Передаем расшифрованный код в request для отображения
                    request.setAttribute("decryptedCode", decryptedCode);  // Здесь передаем расшифрованный код

                    request.setAttribute("info", "Товар успешно куплен");
                    request.getRequestDispatcher("/listProducts").forward(request, response);
                } else {
                    request.setAttribute("info", "На счету недостаточно денег!");
                    request.getRequestDispatcher("/listProducts").forward(request, response);
                }
                break;



            case "/showMyPurchases":
                List<History> historys = historyFacade.findAllForUserByLogin(authUser.getLogin());
                CodeProtector protector = new CodeProtector();

                for (History h : historys) {
                    String decrypted = protector.decrypt(h.getCode().getCode(), h.getCode().getSalt());
                    h.getCode().setCode(decrypted);  // теперь code.code — это уже расшифрованный
                }

                request.setAttribute("historys", historys);
                request.getRequestDispatcher("/WEB-INF/purchases/myPurchases.jsp").forward(request, response);
                break;

        }
    }
    
    private Date localdateToDate(LocalDate dateToConvert){
        return Date.from(dateToConvert.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
