<%-- 
    Document   : productos
    Created on : 8/12/2021, 22:42:28
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
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <!-- Iconos -->
        <script src="https://kit.fontawesome.com/6d0db64a1f.js" crossorigin="anonymous"></script>

        <!-- dataTables -->
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.11.3/datatables.min.css"/>
        <!-- ajax -->
        <script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
        <!-- sweet alert -->
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <!-- js -->
        <script src="../resources/productos.js"></script>
        <title>Productos - Systec</title>
    </head>
    <body>
        <jsp:include page="navegador.jsp"></jsp:include>
            <div class="container mt-5">
                <div class="card">
                    <br>
                    <div class="row g-2 mt-1">
                        <div class="col-md-3 px-4">
                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modalNewProduct">
                                <i class="fas fa-plus"></i> Nuevo producto
                            </button>
                        </div>
                        <div class="col">
                            <a href="${pageContext.request.contextPath}/ControlProductos?tarea=descargarProductosExcel"
                           class="btn btn-success mr-3">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-download" viewBox="0 0 16 16">
                            <path d="M.5 9.9a.5.5 0 0 1 .5.5v2.5a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1v-2.5a.5.5 0 0 1 1 0v2.5a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2v-2.5a.5.5 0 0 1 .5-.5z"/>
                            <path d="M7.646 11.854a.5.5 0 0 0 .708 0l3-3a.5.5 0 0 0-.708-.708L8.5 10.293V1.5a.5.5 0 0 0-1 0v8.793L5.354 8.146a.5.5 0 1 0-.708.708l3 3z"/>
                            </svg> Descargar listado en excel
                        </a>
                    </div>
                </div>
                <div class="card-body">
                    <div class="card-header">
                        <h2 class="text-center">Lista de productos</h2>
                        <h3 class="text-danger text-center">${msje}</h3>
                        <h3 class="text-center text-success">${msjeProducto}</h3>
                        <h3 class="text-center text-success">${msjeNuevoProducto}</h3>
                    </div>
                    <table id="systecTable" class="table table-striped">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Código</th>
                                <th scope="col">Nombre</th>
                                <th scope="col">Precio Unitario</th>
                                <th scope="col">Cantidad existente</th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${productos}" var="producto" varStatus="contador">
                                <tr>
                                    <th>${contador.count}</th>
                                    <td>${producto.codigo}</td>
                                    <td>${producto.nombre}</td>
                                    <td><fmt:formatNumber value="${producto.precioUnitario}" type="currency"/></td>
                                    <c:if test="${producto.cantidadExistente > 0}" >
                                        <td>
                                            ${producto.cantidadExistente}
                                        </td>
                                    </c:if>
                                    <c:if test="${producto.cantidadExistente <=0}">
                                        <td>
                                            Agotado
                                        </td>
                                    </c:if>
                                    <td>
                                        <a href="#" 
                                           class="btn btn-warning"
                                           onclick="openEditProduct('${pageContext.request.contextPath}/ControlProductos?tarea=editProduct&codigo=${producto.codigo}')">
                                            <i class="fas fa-edit"></i> Editar
                                        </a>
                                    </td>
                                </tr> 
                            </c:forEach>
                    </table>
                </div>
            </div>
        </div>
        <div id="modalEditProduct" class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header bg-warning">
                        <h5 class="modal-title" id="exampleModalLabel">Edicion de producto</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body" id="modal-content">
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="modalNewProduct.jsp"></jsp:include>
            <!-- bootStrap -->
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

            <!-- dataTables-->
            <script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
            <script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.11.3/datatables.min.js"></script>
            <script src="../assets/js/dataTables.js" type="text/javascript"></script>
        </body>
    </html>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.addHeader("Pragma", "no-cache");
    if (session.getAttribute("user") == null) {
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
%>
