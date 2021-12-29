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
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/6d0db64a1f.js" crossorigin="anonymous"></script>
        <!-- Iconos -->
        <script src="https://kit.fontawesome.com/6d0db64a1f.js" crossorigin="anonymous"></script>
        <title>Edición producto</title>
    </head>
    <body>
        <jsp:include page="navegador.jsp"></jsp:include>
        <form action="${pageContext.request.contextPath}/ControlProductos?tarea=editarProducto"
              method="POST" class="was-validated">
            <input type="hidden" name="codigo" value="${producto.codigo}""/>
            <div class="row">
                <section class="py-4 mb-4 bg-light mt-5">
                    <div class="container">
                        <div class="col-md-12">
                            <a href="${pageContext.request.contextPath}/ControlProductos?tarea=productos" class="btn btn-warning btn-inline text-center w-50 mb-2">
                                <i class="fas fa-arrow-circle-left"></i> Productos
                            </a>
                            <button type="submit" class="btn btn-primary btn-block text-center w-50">
                                <i class="fas fa-check"></i> Guardar cambios
                            </button>
                        </div>
                    </div>
                </section>
                <section>
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <div class="card">
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
        <!-- Option 1: Bootstrap Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

    </body>
</html>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.addHeader("Pragma", "no-cache");
    if (session.getAttribute("user") == null) {
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
%>

