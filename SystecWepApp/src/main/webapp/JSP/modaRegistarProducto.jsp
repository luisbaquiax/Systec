<%-- 
    Document   : registarProducto
    Created on : 6 dic. 2022, 19:16:45
    Author     : Luis
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Modal -->
<div class="modal fade" id="modalRegistarProducto" tabindex="-1" aria-labelledby="modalRegistarProducto" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered  modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h2 class="modal-title" id="modalNewProduct">Registrar productos</h2>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="card-header">
                    <div class="container">
                        <form method="POST" action="${pageContext.request.contextPath}/ServletRegistroProducto?tarea=registro">
                            <div class="row g-3 mt-1">
                                <div class="col">
                                    <label for="exampleDataList" class="form-label">Productos</label>
                                    <input name="codigoProducto" required="" class="form-control" list="datalistOptions" id="exampleDataList" placeholder="Búsque el producto...">
                                    <datalist id="datalistOptions">
                                        <c:forEach items="${productos}" var="producto">
                                            <option value="${producto.codigo}"/>
                                        </c:forEach>
                                    </datalist>
                                </div>
                                <div class="col">
                                    <label for="exampleDataList" class="form-label">Cantidad a registrar</label>
                                    <input name="unidades" required="" type="number"
                                           class="form-control" placeholder="Unidades" value="1" min="1" aria-describedby="basic-addon1">
                                </div>
                                <div class="col col-md-3">
                                    <br>
                                    <button class="btn btn-primary btn-block" type="submit"><i class="fas fa-save"></i> Guardar cambios</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
