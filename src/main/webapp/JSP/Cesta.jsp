<%-- 
    Document   : Cesta
    Created on : 15-ene-2022, 13:25:51
    Author     : Ana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <%@include file="../INC/metas.inc" %>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
        integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <!-- Mi CSS -->
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/CSS/style.css" media="screen" />

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css"
        integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">

    <title>SmartEngine - Cesta</title>
</head>

<body>

    <c:set var="categorias" value='${applicationScope.categorias}' />

    <!-- Si el usuario está registrado -->
    <c:if test="${sessionScope.usuario!=null}">
        <%@include file="../INC/headerRegistrado.inc" %>
    </c:if>

    <!-- Si el usuario no está registrado -->
    <c:if test="${sessionScope.usuario==null}">
        <%@include file="../INC/headerAnonimo.inc" %>
    </c:if>

    <div class="container">


        <div class="card text-center mt-5 18rem;">


            <div class="card-header">

                <h3 class="card-title text-center">
                    <p>Cesta</p>
                </h3>

                <c:if test="${sessionScope.usuario!=null}">
                    <c:set var="usuario" value="${sessionScope.usuario}" />
                    <h5 class="card-subtitle text-muted">${usuario.nombre}, estos son los productos que has elegido por
                        ahora:</h5>
                </c:if>

                <c:if test="${sessionScope.usuario==null}">
                    <h5 class="card-subtitle text-muted">Usuario an&oacute;nimo, estos son los productos que has elegido
                        por ahora: </h5>
                </c:if>
            </div>


            <div class="card-body">

                <ul class="list-group list-group-flush">

                    <li class="list-group-item">

                        <div class="row">

                            <div class="col-3">
                                <h5>Imagen</h5>
                            </div>

                            <div class="col-2">
                                <h5>Nombre</h5>
                            </div>

                            <div class="col-2">
                                <h5>Marca</h5>
                            </div>

                            <div class="col-2">
                                <h5>Precio ud.</h5>
                            </div>

                            <div class="col-3">
                                <h5>Cantidad</h5>
                            </div>

                        </div>

                    </li>

                    <c:set var="precio" value='${0}' />
                    <!-- DISEÑO TARJETAS PRODUCTO -->

                    <c:set var="productosCesta" value='${sessionScope.cestaSmartengine}' />

                    <c:if test="${productosCesta!=null}">


                        <c:forEach items="${productosCesta}" var="producto">

                            <li class="list-group-item">

                                <div class="row">

                                    <div class="col-3">
                                        <img src="<%= request.getContextPath()%>/IMG/productos/${producto.imagen}.jpg"
                                            alt="imagen producto" class="img-fluid">
                                    </div>

                                    <div class="col-2">
                                        <h5>${producto.nombre}</h5>
                                    </div>

                                    <div class="col-2">
                                        <h5>${producto.marca}</h5>
                                    </div>

                                    <div class="col-2">
                                        <h5>${producto.precioUnitario}€</h5>
                                    </div>

                                    <div class="col-3">
                                        <div class="row">
                                            <div class="col-4">
                                                <button type="" class="btn btn-outline-info" id="udMenos" name="udMenos"
                                                    value="${idProducto}">-</button>
                                            </div>
                                            <div class="col-4">
                                                <h5>${producto.cantidad}</h5>
                                            </div>
                                            <div class="" hidden>
                                                ${producto.idProducto}
                                            </div>
                                            <div class="col-4">
                                                <button type="" class="btn btn-outline-info" id="udMas" name="udMas"
                                                    value="${idProducto}">+</button>
                                            </div>
                                        </div>

                                    </div>

                                    <c:set var="precio" value='${precio+producto.precioUnitario*producto.cantidad}' />

                                </div>

                            </li>


                        </c:forEach>
                    </c:if>



                </ul
            </div> 

             
            <div class="card-footer ">

                <div class="row">

                    <div class="col-6-md col-12-sm ml-5">
                        <h5>Importe total: ${precio}€</h5>
                    </div>

                    <div class="col-6-md ml-5">

                        <div class="row">
                            <div class="col-4-lg col-12-md">
                                <form method="post" action="<%= request.getContextPath()%>/ControladorFinalizarCompra">
    
                                    <button type="submit" class="btn btn-outline-success ml-2" id="finCompra" value="fin"
                                        name="finCompra">Finalizar compra</button>
    
                                </form>
                            </div>
                            <div class="col-4-lg col-12-md">
                                <form method="post" action="<%= request.getContextPath()%>/ControladorEliminarCesta">
    
                                    <button type="submit" class="btn btn-outline-danger ml-2" id="eliminarCesta"
                                        value="eliminarCesta" name="eliminarCesta">Eliminar cesta</button>
    
                                </form>
                            </div>
                            <div class="col-4-lg col-12-md">
    
                                <a href="<%= request.getContextPath()%>/JSP/Tienda.jsp"><button type="submit"
                                        class="btn btn-outline-info ml-2" id="volver" name="volver">Seguir comprando</button></a>
    
                            </div>
                        </div>


                    </div>



                </div>

            </div>

        </div>

    </div>

    <form method="post" id="formCategoria" action="<%= request.getContextPath()%>/ControladorCategoria">

        <input type="text" name="categoria" id="categoriaElegida" value="">

        <input type="submit" value="pulsarCategoria" id="botonCategoria">

    </form>


    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous">
    </script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous">
    </script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous">
    </script>

    <script src="<%= request.getContextPath()%>/JS/categorias.js"></script>
    <script src="<%= request.getContextPath()%>/JS/AJAXcantidad.js"></script>




</body>

</html>