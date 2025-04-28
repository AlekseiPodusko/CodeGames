<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Добро пожаловать в GameCode</title>
    <link rel="stylesheet" href="css/main.css">
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Press+Start+2P&family=Roboto&display=swap');
    </style>
</head>
<body>
    <div class="background-container" style="background-image: url('${pageContext.request.contextPath}/images/Project-in-use/Barotrauma.png');">
        <div class="text-overlay">
            <h1 class="main-title">Добро пожаловать в<br>GameCode</h1>
            <p class="sub-title">Лучшие игры в одном месте</p>
            <a href="${pageContext.request.contextPath}/listProducts" class="btn-game">Перейти к играм</a>
        </div>
    </div>
</body>
</html>
