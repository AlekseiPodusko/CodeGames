<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Назначение роли</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/roleform.css">
</head>
<body>
<div class="role-container">
    <div class="card">
        <div class="card-header">Назначение роли</div>
        <form action="changeRole">
            <div class="card-body">
                <div class="form-group">
                    <label class="form-label">Выбор пользователя</label>
                    <select class="form-select" name="selectUser">
                        <c:forEach var="item" items="${mapUsers}">
                            <option value="${item.key.id}">${item.key.firstName} ${item.value}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label class="form-label">Выбор роли</label>
                    <select class="form-select" name="selectRole">
                        <c:forEach var="role" items="${roles}">
                            <option value="${role.id}">${role.roleName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="d-flex justify-content-center">
                    <button type="submit" class="btn">Назначить</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
