<%-- 
    Document   : Categoria
    Created on : 12-ene-2022, 12:47:40
    Author     : error
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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

        <!-- Mi CSS -->
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/CSS/style.css" media="screen" />

        <title>SmartEngine - Categoria</title>
    </head>
    <body>
        
        <header>

            <c:set var="categorias" value='${applicationScope.categorias}' />


            <!-- Si el usuario está registrado -->
            <c:if test="${sessionScope.usuario!=null}">
                <%@include file="../INC/headerRegistrado.inc" %>                
            </c:if>

            <!-- Si el usuario no está registrado -->
            <c:if test="${sessionScope.usuario==null}">
                <%@include file="../INC/headerAnonimo.inc" %>                
            </c:if>   
                

            
        </header>

        <nav class="navbar navbar-expand-md navbar-dark bg-dark barraHerramientas">

            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample04" aria-controls="navbarsExample04" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarsExample04">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="<%= request.getContextPath()%>/JSP/Tienda.jsp">Inicio <span class="sr-only">(current)</span></a>
                    </li>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="http://example.com" id="dropdown04" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Categorías</a>
                        <div class="dropdown-menu" aria-labelledby="dropdown04">

                          <c:forEach items="${categorias}" var="categoria">

                            <a class="dropdown-item"  id="${categoria.idCategoria}">${categoria.nombre}</a>

                          </c:forEach>

                        </div>
                    </li>
                </ul>
                <form class="form-inline my-2 my-md-0">
                    <a href="#"> <img src="<%= request.getContextPath()%>/IMG/carrito.png" alt="carrito" class=" btn btn_login botonesNavbar"></a>
                    <a href="<%= request.getContextPath()%>/ControladorPerfil"><img src="<%= request.getContextPath()%>/IMG/user.png" alt="usuario" class="btn btn_login botonesNavbar"></a>
                    <a href="<%= request.getContextPath()%>/ControladorCerrarSesion"><img src="<%= request.getContextPath()%>/IMG/exit.png" alt="salir" class="btn btn_login botonesNavbar"></a>
                </form>
            </div>
        </nav>
                
        <div class="container">

            
            <c:set var="productos" value='${requestScope.productosCategoria}' />

            <c:if test="${requestScope.productosCategoria==null}">

                <br> <br>
                <div class="alert alert-dark" role="alert">
                    No hay productos de esta categor&iacute;a.
                  </div>
            </c:if>
            <div class="card-deck">  
                


                <c:if test="${productos!=null}">
                      
                    <c:forEach items="${productos}" var="producto">

                        <div class="col-md-4 col-sm-6">
                            <div class="card text-center" style="width: 18rem;" id="${producto.idProducto}">
                                <div class="embed-responsive embed-responsive-1by1">
                                    <img class="card-img-top embed-responsive-item" src="<%= request.getContextPath()%>/IMG/productos/${producto.imagen}.jpg" alt="Imagen categoria">
                                </div>
                                
                                <div class="card-body">
                                    <p class="card-text"> ${producto.nombre} </p>
                                </div>
                            </div>

                        </div>
                    </c:forEach>
                </c:if>
                
                   
                
                <form method="post" id="formCategoria" action="<%= request.getContextPath()%>/ControladorCategoria">

                  <input type="text" name="categoria" id="categoriaElegida" value="">

                  <input type="submit" value="pulsarCategoria" id="botonCategoria">

                </form>

            </div> 
                
                <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <!-- Mi script -->
        <script src="<%= request.getContextPath()%>/JS/tienda.js"></script>
    </body>
</html>
