<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="css/registration.css">
</head>
<body>
    <div class="login-wrapper">
        <form action="registration" method="POST">
            <div class="login-card">
                <h2 class="login-title">Регистрация</h2>
                <div class="form-group">
                    <label for="firstName" class="form-label">Имя</label>
                    <input type="text" class="form-control" name="firstName" placeholder="Введите имя">
                </div>
                <div class="form-group">
                    <label for="sureName" class="form-label">Фамилия</label>
                    <input type="text" class="form-control" name="sureName" placeholder="Введите фамилию">
                </div>
                <div class="form-group">
                    <label for="email" class="form-label">Почта</label>
                    <input type="text" class="form-control" name="email" placeholder="Введите почту">
                </div>
                <div class="form-group">
                    <label for="login" class="form-label">Имя пользователя</label>
                    <input type="text" class="form-control" name="login" placeholder="Введите логин">
                </div>
                <div class="form-group">
                    <label for="password1" class="form-label">Пароль</label>
                    <input type="password" class="form-control" name="password1" placeholder="Введите пароль">
                </div>
                <div class="form-group">
                    <label for="password2" class="form-label">Подтверждение пароля</label>
                    <input type="password" class="form-control" name="password2" placeholder="Повторите пароль">
                </div>
                <input class="btn-login" type="submit" value="Зарегистрироваться">
                <div class="login-footer">
                    <span class="login-footer">Уже есть аккаунт? </span><a href="showLogin">Войти</a>
                </div>
            </div>
        </form>
    </div>
</body>
</html>
