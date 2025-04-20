<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/productlist.css">

<div class="album">
    <div class="container">
        <c:forEach var="product" items="${products}">
            <div class="card">
                <img 
                    src="${pageContext.request.contextPath}/images/${product.picture.pathToFile}" 
                    alt="Product Image"
                />
                <div class="card-overlay">
                    <h4 class="card-title">${product.title}</h4>
                    <p class="card-text">${product.description}</p>
                    <p class="card-price">Цена: ${product.price}€</p>

                    <div class="card-buttons">
                        <c:if test="${authUser ne null}">
                            <a class="btn-buy" href="showBuyProduct?id=${product.id}">Купить</a>
                        </c:if>

                        <c:if test="${topRole eq 'ADMINISTRATOR'}">
                            <a class="btn-edit" href="showEditProduct?id=${product.id}">Редактировать</a>
                            <form method="post" action="deleteProduct"
                                  onsubmit="return confirm('Вы уверены, что хотите удалить этот продукт?');"
                                  style="display:inline;">
                                <input type="hidden" name="id" value="${product.id}" />
                                <button type="submit" class="btn-delete">Удалить</button>
                            </form>
                        </c:if>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
