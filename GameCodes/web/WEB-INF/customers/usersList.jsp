<%@page contentType="text/html" pageEncoding="UTF-8"%>
<head>
    <title>Редактирование пользователя</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/list.css">
</head>
<div class="album user-list">
    <div class="container">
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-4 justify-content-center">
            <c:forEach var="user" items="${users}">
                <div class="card user-card">
                    <div class="card-header user-card-header">${user.login}</div>
                    <div class="card-body user-card-body">
                        <h4 class="card-title">${user.firstName} ${user.sureName}</h4>
                        <p class="card-text">${user.email}</p>
                        <p class="card-text">${user.cash}€</p>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
