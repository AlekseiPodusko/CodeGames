<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Покупка</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/buyproduct.css">
</head>
<body>

<!-- Счёт в правом верхнем углу с отступами -->
<div class="balance-box">
    <p>Счёт: ${cash}€</p>
    <a href="showAddMoney">Пополнить</a>
</div>

<!-- Основной контейнер -->
<div class="buy-container">

    <!-- Карточка товара -->
    <div class="card">
        <div class="card-header">Информация о товаре</div>
        <div class="card-body">
            <h4 class="card-title">${product.title}</h4>
            <p class="card-text">${product.description}</p>
            <p class="card-text">Цена: ${product.price}€</p>

            <!-- Форма покупки внутри карточки -->
            <form action="buyProduct?id=${product.id}" method="POST" onsubmit="return showLoading()">
                <button type="submit" class="btn-buy">Купить</button>
            </form>
        </div>
    </div>
</div>

<!-- Анимация загрузки — внизу и по центру -->
<div id="loading" class="loading hidden">
    <div class="spinner"></div>
    <p>Платёж оформляется...</p>
</div>

<!-- JS -->
<script>
    function showLoading() {
        document.getElementById('loading').classList.remove('hidden');
        return true;
    }
</script>
</body>
</html>
