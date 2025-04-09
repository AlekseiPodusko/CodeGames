<%@page contentType="text/html" pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <form action="uploadPicture" method="POST" enctype="multipart/form-data">
            <div class="form-group">
                <label for="file">Выберите изображение</label>
                <input type="file" class="form-control" id="file" name="file" required>
            </div>

            <input type="submit" value="Загрузить изображение" class="btn btn-primary">
</form>

                    <li class="btn-sm btn-success my-1 mx-1 mb-1 mt-1"><a class="nav-link" href="showAddProduct">Вернуться к добавлению товара</a></li>
                </form>
            </div>
           </div>
        </div>
