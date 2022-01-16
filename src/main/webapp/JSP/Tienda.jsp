<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">

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

    <title>SmartEngine</title>
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


    <c:set var="masVendido" value='${applicationScope.masVendido}' />
    <div class="jumbotron jumbotron-fluid">
        <div class="container">

            <div class="row">
                <div class="col-12 text-center">
                    <h1>Nuestro producto m&aacute;s vendido</h1>
                    <h2>${masVendido.nombre}</h2>
                </div>
            </div>
            
            

            <div class="row">
                <div class="col-lg-6 col-sm-12 mt-3 d-flex align-items-center">

                    <div>
                        <p class="text-left">
                            ${masVendido.descripcion}
                        </p>
    
                        <p>
                            <h3 class="text-center">
                                ${masVendido.precio}€
                            </h3>
                            
                            <div class="text-center">
                                <button type="button" class="btn btn-outline-dark" id="anadirCesta">Añadir a cesta</button>
                            </div>
                            
                        </p>
                    </div>
                     
                </div>

                <div class="col-lg-6 col-sm-12">
                    <div class="embed-responsive embed-responsive-4by3 imgMasVendido">
                    <img src="<%= request.getContextPath()%>/IMG/productos/${masVendido.imagen}.jpg"
                        alt="Mas vendido" class="embed-responsive-item">
                    </div>
                </div>
            </div>


        </div>
    </div>

    <div class="container">



        <div class="card-deck">

            <c:forEach items="${categorias}" var="categoria">

                <div class="col-lg-4 col-sm-6 contenedorCard">

                    <div class="cara">

                        <div class="card text-center" style="width: 18rem;" id="${categoria.idCategoria}">
                            <div class="embed-responsive embed-responsive-4by3">
                                <img class="card-img-top embed-responsive-item"
                                    src="<%= request.getContextPath()%>/IMG/categorias/${categoria.imagen}"
                                    alt="Imagen categoria">
                            </div>
    
                            <div class="card-body">
                                </p>
                            </div>
    
                            <div class="card-footer">
                                <p class="card-text"> ${categoria.nombre}
                            </div>
                        </div>
                        
                    </div>
                    

                </div>
            </c:forEach>

            <form method="post" id="formCategoria" action="<%= request.getContextPath()%>/ControladorCategoria">

                <input type="text" name="categoria" id="categoriaElegida" value="">

                <input type="submit" value="pulsarCategoria" id="botonCategoria">

            </form>

        </div>


    </div>




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
    <!-- Mi script -->
    <script src="<%= request.getContextPath()%>/JS/tienda.js"></script>
</body>

</html>