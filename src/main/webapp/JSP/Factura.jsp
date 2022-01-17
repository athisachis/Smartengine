<%-- 
    Document   : Factura
    Created on : 15-ene-2022, 20:07:00
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

    <title>SmartEngine - Factura</title>
</head>

<body>

    <%@include file="../INC/headerRegistrado.inc" %>


    <div class="container">

        <div class="card text-center mt-5">

            <div class="card-header">

                <h3 class="card-title text-center">¡Gracias por confiar en Smartengine!</h3>

            </div>

            <div class="card-body">

                <c:set var="importeTotal" value="${requestScope.importeTotal}" />
                <c:set var="iva" value="${requestScope.iva}" />

                <div class="row">

                    <div class="col-6">

                        <div class="embed-responsive embed-responsive-1by1">
                            <img class="card-img-top embed-responsive-item"
                                src="<%= request.getContextPath()%>/IMG/finCompra.png"
                                alt="Imagen categoria">
                        </div>
                    </div>

                    <div class="col-6 text-right">
                        <h3 class="card-text">El importe total asciende a: ${importeTotal}€</h3>
                        <h3 class="card-text">El iva asciende a: ${iva}€</h3>

                        <h3 class="card-text mt-5">¡Disfruta de tu compra!</h3>
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
</body>

</html>