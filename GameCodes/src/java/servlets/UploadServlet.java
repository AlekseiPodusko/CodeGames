package servlets;

import entity.Picture;
import entity.UserRoles;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import session.PictureFacade;

/**
 *
 * @author user
 */
@WebServlet(name = "UploadServlet", urlPatterns = {
    "/addPicture", 
    "/uploadPicture"
})
@MultipartConfig
public class UploadServlet extends HttpServlet {
    @EJB PictureFacade pictureFacade;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String uploadFolder = "C:\\Users\\ProBebra\\Desktop\\GameCodes\\web\\images"; //Добавить дерективу куда надо
        String path = request.getServletPath();
        switch (path) {
            case "/addPicture":
                request.getRequestDispatcher("/WEB-INF/products/addPicture.jsp").forward(request, response);
                break;
            case "/uploadPicture":
                if (uploadFolder == null || uploadFolder.isEmpty()) {
                    request.setAttribute("info", "Ошибка: Путь для загрузки файлов не указан.");
                    request.getRequestDispatcher("/addPicture").forward(request, response);
                    return;
                }

                List<Part> fileParts = request.getParts().stream()
                    .filter(part -> "file".equals(part.getName()))
                    .collect(Collectors.toList());

                if (fileParts.isEmpty()) {
                    request.setAttribute("info", "Ошибка: Файл не был загружен.");
                    request.getRequestDispatcher("/addPicture").forward(request, response);
                    return;
                }

                for (Part filePart : fileParts) {
                    String fileName = getFileName(filePart);
                    if (fileName == null || fileName.isEmpty()) {
                        request.setAttribute("info", "Ошибка: Не удалось определить имя файла.");
                        request.getRequestDispatcher("/addPicture").forward(request, response);
                        return;
                    }

                    File file = new File(uploadFolder, fileName);

                    if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
                        request.setAttribute("info", "Ошибка: Не удалось создать директорию для загрузки.");
                        request.getRequestDispatcher("/addPicture").forward(request, response);
                        return;
                    }

                    try (InputStream fileContent = filePart.getInputStream()) {
                        Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    }

                    Picture picture = new Picture();
                   picture.setPathToFile(fileName); // например, "menufon.png"

                    try {
                        pictureFacade.create(picture);
                    } catch (Exception e) {
                        request.setAttribute("info", "Ошибка при сохранении в базу данных: " + e.getMessage());
                        request.getRequestDispatcher("/addPicture").forward(request, response);
                        return;
                    }
                }

                try {
                    request.setAttribute("info", "Файлы успешно загружены.");
                    request.getRequestDispatcher("/addProduct").forward(request, response);
                } catch (Exception e) {
                    request.setAttribute("info", "Файл добавлен" );
                    request.getRequestDispatcher("/addPicture").forward(request, response);
                }
                break;
            
        }   
        
        
    }
    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")){
            if(content.trim().startsWith("filename")){
                return content
                        .substring(content.indexOf('=')+1)
                        .trim()
                        .replace("\"",""); 
            }
        }
        return null;
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