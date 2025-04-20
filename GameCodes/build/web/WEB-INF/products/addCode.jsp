<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/addproduct.css">
</head>

<div class="add-product-wrapper">
    <form action="addCode" method="POST">
        <div class="add-product-card">
            <h2 class="login-title">Добавить код</h2>

            <!-- Поле для кода -->
            <div class="form-group">
                <label class="form-label">Код</label>
                <input type="text" class="form-control" name="code" value="${code}">
            </div>

            <!-- Выбор продукта -->
            <div class="form-group">
                <label class="form-label">Выберите игру</label>
                <select class="form-control" name="productId">
                    <c:forEach var="product" items="${products}">
                        <option value="${product.id}" ${product.id == productId ? 'selected' : ''}>${product.title}</option>
                    </c:forEach>
                </select>
            </div>

            <!-- Кнопка -->
            <input class="btn-submit" type="submit" value="Добавить код">

            <!-- Инфо-сообщение -->
            <c:if test="${not empty info}">
                <div class="form-label" style="text-align: center; margin-top: 15px; color: #818CF8;">${info}</div>
            </c:if>
        </div>
    </form>
</div>
