<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="css/login.css">
</head>
<body>
    <div class="login-wrapper">
        <form action="login" method="POST">
            <div class="login-card">
                <h2 class="login-title">Вход</h2>
                <div class="form-group">
                    <label for="login" class="form-label">Имя пользователя</label>
                    <input type="text" class="form-control" name="login" value="${name}">
                </div>
                <div class="form-group">
                    <label for="password" class="form-label">Пароль</label>
                    <input type="password" class="form-control" name="password" value="${name}">
                </div>
                <input class="btn-login" type="submit" value="Войти">
                <div class="login-footer">
                    <span class="login-footer">Нет аккаунта? </span><a href="showRegistration">Регистрация</a>
                </div>
            </div>
        </form>
    </div>
</body>
</html>
