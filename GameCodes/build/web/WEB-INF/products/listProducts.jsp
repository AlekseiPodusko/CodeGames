<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="album">
    <div class="container">
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
            <c:forEach var="product" items="${products}">
                <div class="col">
                    <div class="card bg-light mb-3" style="max-width: 20rem;">
                        <div class="card-body">
                            <h4 class="card-title">${product.title}</h4>
                            <p class="card-text" style="text-align: justify">${product.description}</p>
                            <p class="card-text">Цена: ${product.price}€</p>

                            <img 
                                src="${pageContext.request.contextPath}/images/${product.picture.pathToFile}" 
                                alt="Product Image"
                                class="img-fluid"
                                style="max-height: 200px; object-fit: cover;"
                            />


                            <!-- Показываем "Купить", если пользователь залогинен -->
                            <c:if test="${authUser ne null}">
                                <a href="showBuyProduct?id=${product.id}">
                                    <p class="text-info"><strong>Купить</strong></p>
                                </a>
                            </c:if>

                            <!-- Показываем "Редактировать" для администратора и продавца -->
                            <c:if test="${topRole eq 'ADMINISTRATOR'}">
                                <a href="showEditProduct?id=${product.id}">
                                    <p class="text-info"><strong>Редактировать</strong></p>
                                </a>
                            </c:if>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
