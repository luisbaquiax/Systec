<%-- 
    Document   : modalEditProducto
    Created on : 3/06/2022, 23:37:48
    Author     : luis
--%>
<%-- 
    Document   : edicionProducto
    Created on : 18/12/2021, 00:38:08
    Author     : luis
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- para dar formato el texto-->
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- SELECCIONAMOS LA LOCALIDAD -->
<fmt:setLocale value="es_GT"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<div class="card mb-4 shadow-sm">
    <div class="card-body">
        <form action="${pageContext.request.contextPath}/ControlProductos?tarea=editarProducto"
              method="POST" class="was-validated">
            <input type="hidden" name="codigo" value="${producto.codigo}""/>
            <div class="row">
                <section>
                    <div class="container">
                        <div class="col-md-12">
                            <button type="submit" class="btn btn-primary btn-block text-center w-100">
                                <i class="fas fa-check"></i> Guardar cambios
                            </button>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="card mt-1">
                                    <div class="card-header">
                                        <h4>Editar Producto: ${producto.codigo}</h4>
                                    </div>
                                    <div class="card-body">
                                        <div class="form-group">
                                            <label for="nombre">Nombre</label>
                                            <input type="text" class="form-control" name="nombre" required value="${producto.nombre}">
                                        </div>
                                        <div class="form-group">
                                            <label for="precioUnitario">Precio unitario (Q)</label>
                                            <input type="number" step="0.1" class="form-control" name="precioUnitario" required 
                                                   value="${producto.precioUnitario}"/>
                                        </div>
                                        <div class="form-group">
                                            <label for="cantidadExistente">Cantidad en existencia</label>
                                            <input type="number" class="form-control" name="cantidadExistente" required value="${producto.cantidadExistente}" min="0">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        </form>
    </div>
</div>


