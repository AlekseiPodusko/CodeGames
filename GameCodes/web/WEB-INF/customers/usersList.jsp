
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="album">
    <div class="container">
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
            <c:forEach var="user" items="${users}">
                <div class="card bg-light" style="max-width: 15rem;">
                    <div class="card-header text-center text-warning">${user.login}</div>
                    <div class="card-body">
                        <h4 class="card-title">${user.firstName} ${user.sureName}</h4>
                        <p class="card-text">${user.phone}</p>
                        <p class="card-text">${user.cash}€</p>
                    </div>
                </div>  
            </c:forEach>
        </div>
    </div>
</div>

<!--<div class="w-100 d-flex justify-content-center">               
    <div class="form-group">
        <label class="form-label mt-4">Example select</label>
        <select class="form-select" id="exampleSelect1">
            <%--<c:forEach var="user" items="${users}">--%>
                <option>${user.login}</option>
            <%--</c:forEach>--%>
        </select>
    </div>
</div>-->