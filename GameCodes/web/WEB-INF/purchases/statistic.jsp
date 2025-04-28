<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Статистика доходов</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/income.css">
</head>
<body>
<div class="income-container">
    <div class="card-income">
        <div class="card-header-income">Доход за ${month}</div>
        <div class="card-body-income">
            <p class="income-text">${monthIncome}€</p>
        </div>
    </div>

    <div class="card-income">
        <div class="card-header-income">Доход за все время</div>
        <div class="card-body-income">
            <p class="income-text">${allIncome}€</p>
        </div>
    </div>
</div>
</body>
</html>
