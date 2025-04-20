<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/addproduct.css">
</head>
<body>
    <div class="add-product-wrapper">
        <form action="addProduct" method="POST">
            <div class="add-product-card">
                <h2 class="login-title">Добавить игру</h2>

                <div class="form-group">
                    <label class="form-label">Название</label>
                    <input type="text" class="form-control" name="name" value="${name}">
                </div>

                <div class="form-group">
                    <label class="form-label">Описание</label>
                    <textarea class="form-control" name="description" rows="3">${description}</textarea>
                </div>

                <!-- Блок цены с евро теперь поднят выше -->
                <div class="form-group">
                    <label class="form-label">Цена</label>
                    <div class="input-group">
                        <span class="input-group-text">€</span>
                        <input type="number" min="1.00" step="0.01" name="price" class="form-control" value="${price}">
                    </div>
                </div>

               

                <div class="form-group">
                    <label class="form-label">Выбрать изображение</label>
                    <select class="form-control" name="pictureid">
                        <c:forEach var="picture" items="${pictures}">
                            <option value="${picture.pictureid}">${picture.pathToFile}</option>
                        </c:forEach>
                    </select>
                </div>
                     <div class="d-flex justify-content-center">
                    <a class="btn-success my-1 mx-1 mb-1 mt-1" href="addPicture">Вставить изображение</a>
                </div>

                <input class="btn-submit" type="submit" value="Добавить товар">
            </div>
        </form>
    </div>
</body>
</html>
