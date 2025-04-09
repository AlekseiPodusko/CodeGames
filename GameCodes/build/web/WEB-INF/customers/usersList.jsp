<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="album">
    <div class="container">
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
            <c:forEach var="user" items="${users}">
                <div class="card custom-card">
                    <div class="card-header custom-card-header">${user.login}</div>
                    <div class="card-body custom-card-body">
                        <h4 class="card-title">${user.firstName} ${user.sureName}</h4>
                        <p class="card-text">${user.email}</p>
                        <p class="card-text">${user.cash}€</p>
                    </div>
                </div>  
            </c:forEach>
        </div>
    </div>
</div>

        </select>
    </div>
</div>-->