<%-- 
    Document   : menuPrincipal
    Created on : 8/12/2021, 00:04:09
    Author     : luis
--%>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
        <meta name="generator" content="Hugo 0.88.1">

        <link rel="canonical" href="https://getbootstrap.com/docs/5.1/examples/sidebars/">
        <!-- Bootstrap core CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <style>
            .bd-placeholder-img {
                font-size: 1.125rem;
                text-anchor: middle;
                -webkit-user-select: none;
                -moz-user-select: none;
                user-select: none;
            }

            @media (min-width: 768px) {
                .bd-placeholder-img-lg {
                    font-size: 3.5rem;
                }
            }
         
        </style>
        <!-- Custom styles for this template -->
        <link href="../assets/css/nabar.css" rel="stylesheet">
        <!-- Iconos -->
        <script src="https://kit.fontawesome.com/6d0db64a1f.js" crossorigin="anonymous"></script>
        <title>Menu Principal</title>
    </head>
    <body>
        <jsp:include page="navegador.jsp"></jsp:include>
            <!-- 
            <div class="container mt-5 py-5 text-center">
                    <div class="card">
                        <div class="card-header">
                            <h1 class="text-center">Menu</h1>
                        </div>
                        <div class="card-body">
                            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                                <li class="nav-item mt-3 mb-4">
                                    <a class="nav-link btn btn-warning btn-block w-100" aria-current="page" href="${pageContext.request.contextPath}/ControlVentas?tarea=nuevaVenta">Nueva venta</a>
                            </li>
                            <li class="nav-item mb-4 mt-3">
                                <a class="nav-link btn btn-warning btn-block w-100" href="${pageContext.request.contextPath}/ControlVentas?tarea=ventas">Ventas</a>
                            </li>
                            <li class="nav-item mt-3">
                                <a class="nav-link btn btn-warning btn-block w-100" href="${pageContext.request.contextPath}/ControlProductos?tarea=productos">Productos</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <script src="../assets/js/sidebars.js"></script>
    </body>
</html>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.addHeader("Pragma", "no-cache");
    if (session.getAttribute("user") == null) {
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
%>
