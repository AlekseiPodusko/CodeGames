<%@page contentType="text/html" pageEncoding="UTF-8"%>
<head>
    <meta charset="UTF-8">
    <title>Добавление денег</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypurchase.css">
</head>
<div class="album">
    <div class="container">
        <div class="row">
            <c:forEach var="history" items="${historys}">
                <div class="col">
                    <div class="card">
                        <div class="card-body">
                            
                            <h4 class="card-title">${history.product.title}</h4>
                            <p class="card-text">${history.product.description}</p>
                            <p class="card-price">Цена: ${history.product.price}€</p>
                            <p class="card-text">Код: ${history.code.code}</p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
