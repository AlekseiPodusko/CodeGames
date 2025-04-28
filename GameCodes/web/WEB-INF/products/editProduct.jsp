<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Изменить товар</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/addproduct.css">
</head>
<body>
    <div class="add-product-wrapper">
        <form action="editProduct?id=${id}" method="POST" class="add-product-card">
            <h2 class="login-title">Изменить товар</h2>

            <div class="form-group">
                <label class="form-label">Название</label>
                <input type="text" class="form-control" name="name" value="${product.title}">
            </div>

            <div class="form-group">
                <label class="form-label">Описание</label>
                <textarea class="form-control" name="description" rows="3">${product.description}</textarea>
            </div>

            <div class="form-group">
                <label class="form-label">Цена</label>
                <div class="input-group">
                    <span class="input-group-text">€</span>
                    <input type="number" min="1.00" step="0.01" name="price" class="form-control" value="${product.price}">
                </div>
            </div>

               
            <input type="submit" value="Изменить товар" class="btn-submit">
        </form>
    </div>
</body>
</html>
