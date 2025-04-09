<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="w-100 d-flex justify-content-center">
    <form action="addCode" method="POST">
        <div class="card border-0 mb-3 px-3" style="width: 40em;">

            <h4 class="mt-3 text-center">Добавить код</h4>

            <!-- Поле для кода -->
            <div class="form-group">
                <label class="form-label mt-2 mx-2">Код</label>
                <input type="text" class="form-control" name="code" value="${code}">
            </div>

            <!-- Выбор продукта -->
            <div class="form-group">
                <label class="form-label mt-2 mx-2">Выберите игру</label>
                <select class="form-control" name="productId">
                    <c:forEach var="product" items="${products}">
                        <option value="${product.id}" ${product.id == productId ? 'selected' : ''}>${product.title}</option>
                    </c:forEach>
                </select>
            </div>

            <!-- Кнопка -->
            <input class="btn btn-primary my-3" type="submit" value="Добавить код">

            <!-- Инфо-сообщение -->
            <c:if test="${not empty info}">
                <div class="alert alert-info text-center mt-2">${info}</div>
            </c:if>
        </div>
    </form>
</div>
