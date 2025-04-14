<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="showMain.jsp">GameCode</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarContent">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse justify-content-end" id="navbarContent">
            <ul class="navbar-nav align-items-center">
                <c:choose>
                    <c:when test="${topRole eq 'ADMINISTRATOR'}">
                        <li class="nav-item"><a class="nav-link" href="showMain.jsp">Главная</a></li>
                        <li class="nav-item"><a class="nav-link" href="showAddProduct">Добавить игру</a></li>
                        <li class="nav-item"><a class="nav-link" href="showAddCode">Добавить код</a></li>
                        <li class="nav-item"><a class="nav-link" href="listProducts">Список игр</a></li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="adminDropdown" role="button" data-bs-toggle="dropdown">Администрирование</a>
                            <ul class="dropdown-menu" aria-labelledby="adminDropdown">
                                <li><a class="dropdown-item" href="showChangeRole">Назначить роль</a></li>
                                <li><a class="dropdown-item" href="showStatistic">Статистика</a></li>
                                <li><a class="dropdown-item" href="showEditUser">Редактировать</a></li>
                                <li><a class="dropdown-item" href="showUsersList">Пользователи</a></li>
                            </ul>
                        </li>
                        <li class="nav-item"><a class="nav-link" href="showMyPurchases">Мои покупки</a></li>
                        <li class="nav-item"><a class="nav-link" href="showAddMoney">Добавить денег</a></li>
                    </c:when>

                    <c:when test="${topRole eq 'CUSTOMER'}">
                        <li class="nav-item"><a class="nav-link" href="showMain.jsp">Главная</a></li>
                        <li class="nav-item"><a class="nav-link" href="listProducts">Список игр</a></li>
                        <li class="nav-item"><a class="nav-link" href="showEditUser">Редактировать</a></li>
                        <li class="nav-item"><a class="nav-link" href="showMyPurchases">Мои покупки</a></li>
                        <li class="nav-item"><a class="nav-link" href="showAddMoney">Добавить денег</a></li>
                    </c:when>

                    <c:when test="${topRole eq NULL}">
                        <li class="nav-item"><a class="nav-link" href="showMain.jsp">Главная</a></li>
                        <li class="nav-item"><a class="nav-link" href="listProducts">Игры</a></li>
                    </c:when>
                </c:choose>

                <c:if test="${authUser eq null}">
                    <li class="nav-item btn-purple"><a href="showLogin">Войти</a></li>
                </c:if>
                <c:if test="${authUser ne null}">
                    <li class="nav-item btn-purple"><a href="logout">Выйти</a></li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
