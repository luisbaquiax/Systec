<%-- 
    Document   : ventas
    Created on : 8/12/2021, 00:50:11
    Author     : luis
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <!-- Iconos -->
        <script src="https://kit.fontawesome.com/6d0db64a1f.js" crossorigin="anonymous"></script>
        <title>Ventas</title>
    </head>
    <body>
        <jsp:include page="navegador.jsp"></jsp:include>
            <div class="container mt-5">
                <div class="card">
                    <div class="card-header">
                        <h2 class="text-center">Ventas</h2>
                    </div>
                    <div class="card-body">
                        <table class="table table-striped">
                            <thead class="thead-dark">
                                <tr>
                                    <th>#</th>
                                    <th>Identificador de la venta</th>
                                    <th>Ver detalle</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="venta" items="${ventas}" varStatus="contador" >
                                <tr>
                                    <th>${contador.count}</th>
                                    <td>${venta.id}</td>
                                    <td> 
                                        <a href="${pageContext.request.contextPath}/ControlVentas?tarea=verDetalleVenta&id=${venta.id}"
                                           class="btn btn-warning">
                                            <i class="fas fa-chevron-double-down"></i> Ver detalle
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                    </table>
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
