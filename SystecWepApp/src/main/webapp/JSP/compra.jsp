<%-- 
    Document   : compra
    Created on : 5 dic. 2022, 19:27:47
    Author     : Luis
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
        <!-- dataTables -->
        <script src="../assets/js/dataTables.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.11.3/datatables.min.css"/>
        <title>Ventas</title>
    </head>
    <body>
        <jsp:include page="navegador.jsp"></jsp:include>
            <div class="container mt-5">
                <div class="card">
                    <div class="card-header">
                        <section id="actions" class="mb-4 bg-light">
                            <div class="container">
                                <form method="POST" action="${pageContext.request.contextPath}/ServletRegistroProducto?tarea=registro">
                                <div class="row">
                                    <h1 class="text-center"> Registrar producto</h1>
                                    <h2 class="mt-3">${registroProduct}</h2>
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
                                        <label for="exampleDataList" class="form-label">Cantidad a registrar</label>
                                        <input name="unidades" required="" type="number"
                                               class="form-control" placeholder="Unidades" value="1" min="1" aria-describedby="basic-addon1">
                                    </div>
                                    <div class="col col-md-3">
                                        <br>
                                        <button class="btn btn-primary btn-block" type="submit"><i class="fas fa-save"></i> Guardar cambios</button>
                                    </div>
                                    <div class="col col-md-1">
                                        <br>
                                        <a href="${pageContext.request.contextPath}/DescargaExcelRegistroProductos?tarea=hola" 
                                           class="btn btn-success btn-block w-100" ><i class="fas fa-table"> Excel</i></a>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </section>
                </div>
                <div class="card-body">
                    <table id="systecTable" class="table table-striped">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Codigo producto</th>
                                <th scope="col">Fecha</th>
                                <th scope="col">Unidades</th>
                                <th scope="col">Acción</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${registros}" var="c" varStatus="contador">
                                <tr>
                                    <th>${c.id}</th>
                                    <th>${c.producto}</th>
                                    <td>${c.fecha}</td>
                                    <td>${c.unidades}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/ServletRegistroProducto?tarea=candelar&id=${c.id}" 
                                           class="btn btn-danger" ><i class="fas fa-trash-alt"></i> Cancelar</a>
                                    </td>
                                </tr>
                            </c:forEach>
                    </table>
                </div>
            </div>
        </div>
        <!-- Option 1: Bootstrap Bundle with Popper -->
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
