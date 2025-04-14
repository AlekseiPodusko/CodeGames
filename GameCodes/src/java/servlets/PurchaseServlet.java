
package servlets;

import entity.Code;
import entity.Picture;
import entity.Product;
import entity.User;
import session.ProductFacade;
import session.UserRolesFacade;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.CodeFacade;
import session.PictureFacade;
import tools.CodeProtector;
import tools.PasswordProtector;

@WebServlet(name = "SellerServlet", urlPatterns = {
    "/showAddProduct",
    "/addProduct",
    "/showEditProduct",
    "/editProduct",
    "/showAddCode",
    "/addCode",
    
    
})
public class PurchaseServlet extends HttpServlet {
    @EJB UserRolesFacade userRolesFacade;
    @EJB ProductFacade productFacade;
    @EJB private PictureFacade pictureFacade;
    @EJB private CodeFacade codeFacade;

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String uploadFolder = "C:\\Users\\smeke\\OneDrive\\Документы\\NetBeansProjects\\GameCodes";        
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
        session.setAttribute("topRole", userRolesFacade.getTopRole(authUser));
        
        String path = request.getServletPath();
        switch(path) {
            case "/showAddProduct":
                List<Picture> pictures = pictureFacade.findAll(); // Загружаем все картинки из БД
                request.setAttribute("pictures", pictures); 
                request.getRequestDispatcher("/WEB-INF/products/addProduct.jsp").forward(request, response);
                break;
                
            case "/addProduct":
                String name = request.getParameter("name");
                String description = request.getParameter("description");
                String price = request.getParameter("price");
                String pictureid = request.getParameter("pictureid");

                if (name.isEmpty() || description.isEmpty() || price.isEmpty() || pictureid.isEmpty()) {
                    request.setAttribute("name", name);
                    request.setAttribute("description", description);
                    request.setAttribute("price", price);
                    request.setAttribute("pictureid", pictureid);
                    request.setAttribute("info", "Заполните все поля!");
                    request.getRequestDispatcher("/showAddProduct").forward(request, response);
                    break;
                }

                Product product = new Product();
                product.setTitle(name);
                product.setDescription(description);
                product.setPrice(Double.parseDouble(price));

                // Получаем картинку по ID
                Picture picture = pictureFacade.find(Long.parseLong(pictureid));
                product.setPicture(picture);  // Устанавливаем объект Picture в продукт

                productFacade.create(product);

                request.setAttribute("info", "Товар успешно добавлен");
                request.getRequestDispatcher("/showAddProduct").forward(request, response);
                break;


                
            case "/showEditProduct":
                String productId = request.getParameter("id");
                product = productFacade.find(Long.parseLong(productId));
                request.setAttribute("id", productId);
                request.setAttribute("product", product);
                request.getRequestDispatcher("/WEB-INF/products/editProduct.jsp").forward(request, response);
                break;
                
            case "/editProduct":
                productId = request.getParameter("id");
                name = request.getParameter("name");
                description = request.getParameter("description");
                price = request.getParameter("price");
                
                if (name.isEmpty() || description.isEmpty() || price.isEmpty() ) {
                    request.setAttribute("name", name);
                    request.setAttribute("desctiption", description);
                    request.setAttribute("price", price);
                    request.setAttribute("info", "Заполните все поля!");
                    request.getRequestDispatcher("/showEditProduct").forward(request, response);
                    break;
                }
                
                product = productFacade.find(Long.parseLong(productId));
                product.setTitle(name);
                product.setDescription(description);
                productFacade.edit(product);
                request.setAttribute("info", "Товар успешно обновлен");
                request.getRequestDispatcher("/listProducts").forward(request, response);
                break;
                
            case "/showAddCode":
                List<Product> products = productFacade.findAll();
                request.setAttribute("products", products);
                request.getRequestDispatcher("/WEB-INF/products/addCode.jsp").forward(request, response);
                break;

                case "/addCode":
                String rawCode = request.getParameter("code");
                String codeProductId = request.getParameter("productId");

                if (rawCode == null || rawCode.isEmpty() || codeProductId == null || codeProductId.isEmpty()) {
                    request.setAttribute("code", rawCode);
                    request.setAttribute("productId", codeProductId);
                    request.setAttribute("info", "Заполните все поля!");
                    request.getRequestDispatcher("/addCode.jsp").forward(request, response);
                    break;
                }

                // Используем CodeProtector для шифрования
                CodeProtector protector = new CodeProtector();
                String salt = protector.getSalt();
                String protectedCode = protector.encrypt(rawCode, salt);

                Product codeProduct = productFacade.find(Long.parseLong(codeProductId));
                Code codeEntity = new Code();
                codeEntity.setCode(protectedCode);
                codeEntity.setSalt(salt);
                codeEntity.setPurchase("no"); // Жёстко "нет"
                codeEntity.setGame(codeProduct);

                codeFacade.create(codeEntity);
                response.sendRedirect("listProducts");

                break;




                    }
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