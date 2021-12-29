<%-- 
    Document   : detalleVenta
    Created on : 8/12/2021, 22:10:18
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
        <title>Productos - Systec</title>
    </head>
    <body>
        <jsp:include page="navegador.jsp"></jsp:include>
            <div class="container mt-5">
                <div class="card">
                    <div class="card-body">
                        <div class="card-header">
                            <h2 class="text-center">
                                <a href="${pageContext.request.contextPath}/ControlVentas?tarea=ventas" class="btn btn-primary btn-inline text-center mb-2">
                                <i class="fas fa-arrow-circle-left"></i></a>
                            Detalle de la venta</h2>
                        <hr>
                        <h4>Fecha: ${fecha}</h4>
                        <h4>Usuario: ${user.codigo}</h4>
                    </div>
                    <table class="table table-striped">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Código</th>
                                <th scope="col">Precio Unitario</th>
                                <th scope="col">Cantidad</th>
                                <th scope="col">Total parcial</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${factura}" var="fa" varStatus="contador">
                                <tr>
                                    <td>${contador.count}</td>
                                    <td>${fa.codigoProducto}</td>
                                    <td><fmt:formatNumber value="${fa.precioUnitario}" type="currency"/></td>
                                    <td>${fa.cantidadProductos}</td>
                                    <td><fmt:formatNumber value="${fa.totalPago}" type="currency"/></td>
                                </tr> 
                            </c:forEach>
                            <tr>
                                <th></th>
                                <td></td>
                                <td></td>
                                <td><b>Total a pagar:</b></td>
                                <td><b><fmt:formatNumber value="${total}" type="currency"/></b></td>
                            </tr>
                    </table>
                </div>
            </div>
        </div>
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
