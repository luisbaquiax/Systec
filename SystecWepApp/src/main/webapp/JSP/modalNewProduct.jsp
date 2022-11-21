<%-- 
    Document   : modalNewProduct
    Created on : 20 nov. 2022, 14:29:17
    Author     : Luis
--%>

<!-- Modal -->
<div class="modal fade" id="modalNewProduct" tabindex="-1" aria-labelledby="modalNewProduct" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h2 class="modal-title" id="modalNewProduct">Nuevo producto</h2>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="card-header">
                    <div class="container">
                        <form method="POST" action="${pageContext.request.contextPath}/ControlProductos?tarea=nuevoProducto">
                            <div class="row g-12 mt-1">
                                <div class="row">
                                    <label for="exampleDataList" class="form-label">Código:</label>
                                    <input name="codigo" type="text" class="form-control" placeholder="Código de producto" aria-describedby="basic-addon1" required="">
                                </div>
                                <div class="row">
                                    <label for="exampleDataList" class="form-label">Nombre del producto:</label>
                                    <input name="nombreProducto" type="text" class="form-control" placeholder="Nombre" aria-describedby="basic-addon1" required="">
                                </div>
                                <div class="row">
                                    <label for="exampleDataList" class="form-label">Cantidad de existencia:</label>
                                    <input name="cantidadExistente" type="number" class="form-control" placeholder="Cantidad existente" value="1" min="1" aria-describedby="basic-addon1">
                                </div>
                                <div class="row">
                                    <label for="exampleDataList" class="form-label">Precio unitario (Q):</label>
                                    <input name="precioUnitario" type="number" class="form-control" placeholder="Precio" value="1" min="0" step="0.05" required="">
                                </div>
                                <div class="row modal-footer text-center">
                                    <button type=submit class="btn btn-primary">Guardar producto</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
