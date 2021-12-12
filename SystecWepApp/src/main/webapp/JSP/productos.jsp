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
        <title>Productos - Systec</title>
    </head>
    <body>
        <jsp:include page="navegador.jsp"></jsp:include>
            <div class="container mt-5">
                <div class="card">
                    <div class="card-header">
                        <!-- Botones de navegación para agregar cliente-->
                        <section id="actions" class="py-4 bg-light">
                            <div class="container">
                                <form method="POST" action="${pageContext.request.contextPath}/ControlProductos?tarea=nuevoProducto">
                                <h2 class="text-center">Nuevo producto</h2>
                                <div class="row">
                                    <div class="col col-md-8">
                                        <button class="btn btn-primary">Guardar producto</button>
                                    </div>
                                </div>
                                <div class="row g-12 mt-1">
                                    <div class="col">
                                        <label for="exampleDataList" class="form-label">Código:</label>
                                        <input name="codigo" type="text" class="form-control" placeholder="Código de producto" aria-describedby="basic-addon1" required="">
                                    </div>
                                    <div class="col">
                                        <label for="exampleDataList" class="form-label">Nombre del producto:</label>
                                        <input name="nombreProducto" type="text" class="form-control" placeholder="Nombre" aria-describedby="basic-addon1" required="">
                                    </div>
                                    <div class="col">
                                        <label for="exampleDataList" class="form-label">Precio unitario (Q):</label>
                                        <input name="precioUnitario" type="number" class="form-control" placeholder="Precio" value="1" min="1" step="0.05" required="">
                                    </div>
                                    <div class="col">
                                        <label for="exampleDataList" class="form-label">Cantidad de existencia:</label>
                                        <input name="cantidadExistente" type="number" class="form-control" placeholder="Cantidad existente" value="1" min="1" aria-describedby="basic-addon1">
                                    </div>
                                </div>
                            </form>
                        </div>
                    </section>
                </div>
                <div class="card-body">
                    <div class="card-header">
                        <h2 class="text-center">Lista de productos</h2>
                    </div>
                    <table class="table table-striped">
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
                                    <td>${producto.cantidadExistente}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/ControlProductos?tarea=editProduct&codigo=${producto.codigo}"
                                           class="btn btn-warning">
                                            <i class="fas fa-edit"></i> Editar
                                        </a>
                                    </td>
                                </tr> 
                            </c:forEach>


                    </table>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

    </body>
</html>