<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Добавление денег</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/addmoney.css">
</head>
<body>
    <div class="add-money-container">
        <form action="addMoney" method="POST">
            <div class="card">
                <div class="card-header">Добавление денег</div>
                <div class="card-body">
                    <div class="form-group">
                        <label class="form-label">Сумма пополнения</label>
                        <div class="input-group mb-3">
                            <span class="input-group-text">€</span>
                            <input type="number" min="5" max="500" step="5" class="form-control" name="money" required>
                        </div>
                    </div>
                    <button type="submit" class="btn-success">Внести деньги</button>
                </div>
            </div>
        </form>
    </div>
</body>
</html>
