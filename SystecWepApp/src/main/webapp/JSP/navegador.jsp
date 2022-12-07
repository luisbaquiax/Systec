<%-- 
    Document   : navegador
    Created on : 8/12/2021, 00:51:44
    Author     : luis
--%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top mb-4" aria-label="">
    <div class="container-fluid">
        <a class="navbar-brand" href="menuPrincipal.jsp"><i class="fas fa-home"></i>Systec</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample05" aria-controls="navbarsExample05" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarsExample05">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/ControlVentas?tarea=nuevaVenta">Nueva venta</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/ControlVentas?tarea=ventas">Reporte de ventas</a>
                <li class="nav-item dropdown">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/ControlProductos?tarea=productos">Productos</a>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/ServletRegistroProducto?tarea=registrarProducto">Registro de productos</a>
            </ul>
            <div class="col-md-3 mr-3">
                <a href="${pageContext.request.contextPath}/Salir" class="btn btn-ligth btn-block btn-primary">
                    <i class="fas fa-arrow-left"></i> Salir
                </a>
            </div>
        </div>

    </div>
</nav>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.addHeader("Pragma", "no-cache");
    if (session.getAttribute("user") == null) {
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
%>


