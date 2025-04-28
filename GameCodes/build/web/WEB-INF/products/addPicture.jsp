<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <!-- Ссылка на внешний CSS файл -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/addproduct.css">
</head>

<form action="uploadPicture" method="POST" enctype="multipart/form-data">
    <div class="add-product-wrapper">
        <div class="add-product-card">
            <!-- Заголовок -->
            <h2 class="login-title">Добавить изображение</h2>

            <!-- Кнопка "Вернуться к добавлению товара" -->
            <li class="btn-sm btn-success my-1 mx-1 mb-1 mt-1 btn-reverse">
                <a class="nav-link" href="showAddProduct">Вернуться к добавлению товара</a>
            </li>

            <!-- Поле для загрузки файла (с измененным дизайном) -->
            <div class="form-group form-reversed">
                <label for="file" class="form-label">Выберите изображение</label>
                <input type="file" class="form-control form-reversed-input" id="file" name="file" required>
            </div>

            <!-- Кнопка загрузки -->
            <input type="submit" value="Загрузить изображение" class="btn-submit">
        </div>
    </div>
</form>
