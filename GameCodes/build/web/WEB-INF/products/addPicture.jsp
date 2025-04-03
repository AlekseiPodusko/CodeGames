<%@page contentType="text/html" pageEncoding="UTF-8"%>
        
        <div class="w-100 d-flex justify-content-center p-5">
           <div class="card border-0" style="width: 38rem;">
            <div class="card-body">
                <form action="uploadPicture" method="POST" enctype="multipart/form-data">
                    <div class="mb-3 row">
                        <label for="fileName" class="col-sm-4 col-form-label">Изображение игры:</label>
                        <div class="col-sm-8">
                          <input type="file" class="form-control" id="fileName" name="file" value="">
                        </div>
                    </div>
                    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                        <button type="submit" class="btn btn-primary me-md-2">Загрузить</button>
                    </div>
                    <li class="btn-sm btn-success my-1 mx-1 mb-1 mt-1"><a class="nav-link" href="showAddProduct">Вернуться к добавлению товара</a></li>
                </form>
            </div>
           </div>
        </div>