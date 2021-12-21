<%-- 
    Document   : venta
    Created on : 8/12/2021, 19:35:02
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
        <title>Nueva venta</title>
    </head>
    <body>
        <jsp:include page="navegador.jsp"></jsp:include>
            <div class="container mt-5">
                <div class="card">
                    <div class="card-header">
                        <!-- Botones de navegación para agregar cliente-->
                        <section id="actions" class="py-4 mb-4 bg-light">
                            <div class="container">
                                <form method="POST" action="${pageContext.request.contextPath}/ControlVentas?tarea=agregarProductoFactura">
                                <div class="row">
                                    <div class="col col-md-3">
                                        <button class="btn btn-primary btn-block" type="submit"><i class="fas fa-plus"></i> Agregar Producto</button>
                                    </div>
                                    <h2 class="mt-3">${msjeVenta}</h2>
                                </div>
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
                                        <label for="exampleDataList" class="form-label">Cantidad:</label>
                                        <input name="cantidadProducto" required="" type="number"
                                               class="form-control" placeholder="Username" value="1" min="1" aria-describedby="basic-addon1">
                                    </div>
                                </div>
                            </form>
                        </div>
                    </section>

                </div>
                <div class="card-body">
                    <table class="table table-striped">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Producto</th>
                                <th scope="col">Costo Unitario</th>
                                <th scope="col">Cantidad</th>
                                <th scope="col">Total parcial</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${productosFactura}" var="factura" varStatus="contador">
                                <tr>
                                    <th>${contador.count}</th>
                                    <th>${factura.codigoProducto}</th>
                                    <td><fmt:formatNumber value="${factura.precioUnitario}" type="currency"/></td>
                                    <td>${factura.cantidadProductos}</td>
                                    <td><fmt:formatNumber value="${factura.totalPago}" type="currency"/></td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/ControlVentas?tarea=quitarProducto&codigo=${factura.codigoProducto}" 
                                           class="btn btn-danger btn-block w-100" ><i class="fas fa-trash-alt"></i> Quitar</a>
                                    </td>
                                </tr>
                            </c:forEach>

                            <tr>
                                <th></th>
                                <td></td>
                                <td></td>
                                <td><b>Total a pagar:</b></td>
                                <td><b><fmt:formatNumber value="${total}" type="currency"/></b></td>
                                <td></td>
                            </tr>
                    </table>
                </div>
                <div class="card car-footer">
                    <a href="${pageContext.request.contextPath}/ControlVentas?tarea=realizarVenta" 
                       class="btn btn-warning btn-block w-100" >Finalizar Venta</a>
                </div>
            </div>
        </div>
        <!-- Option 1: Bootstrap Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

    </body>
</html>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    if (session.getAttribute("user") == null) {
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
%>
