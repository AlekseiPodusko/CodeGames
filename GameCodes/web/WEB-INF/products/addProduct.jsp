<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="w-100 d-flex justify-content-center">
    <form action="addProduct" method="POST">
        <div class="card border-0 mb-3 px-3" style="width: 40em;">
            <div class="d-flex justify-content-center">
                <a class="btn-sm btn-success my-1 mx-1 mb-1 mt-1" href="addPicture">Вставить Изображение</a>
            </div>
           
            <div class="form-group">
                <label class="form-label mt-2 mx-2">Выбрать изображение</label>
                <select class="form-control" name="pictureid">
                    <c:forEach var="picture" items="${pictures}">
                        <option value="${picture.pictureid}">${picture.pathToFile}</option>
                    </c:forEach>
                </select>
            </div>

            
            <div class="form-group">
                <label class="form-label mt-2 mx-2">Название</label>
                <input type="text" class="form-control" name="name" value="${name}">
            </div>
            <div class="form-group">
                <label class="form-label mt-2 mx-2">Описание</label>
                <textarea class="form-control" name="description" rows="3">${description}</textarea>
            </div>
            <div class="form-group">
                <label class="form-label mt-2 mx-2">Цена</label>
                <div class="input-group">
                    <span class="input-group-text">€</span>
                    <input type="number" min="1.00" step="0.01" name="price" class="form-control" value="${price}">
                </div>
            </div>
            
            <input class="btn btn-primary mb-2" type="submit" value="Добавить товар">
        </div>
    </form>
</div>
