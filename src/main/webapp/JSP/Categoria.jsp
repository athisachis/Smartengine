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
            <!-- <c:if test="${sessionScope.usuario!=null}">
                <%@include file="../INC/headerRegistrado.inc" %>                
            </c:if> -->

            <!-- Si el usuario no está registrado -->
            <!-- <c:if test="${sessionScope.usuario==null}">
                <%@include file="../INC/headerAnonimo.inc" %>                
            </c:if> -->   
                

        </header>

        <nav class="navbar navbar-expand-md navbar-dark bg-dark barraHerramientas">

            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample04" aria-controls="navbarsExample04" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarsExample04">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="#">Inicio <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Nosotros</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Contacto</a>
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
                
                
    </body>
</html>
