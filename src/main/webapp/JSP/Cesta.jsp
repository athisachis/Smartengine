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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

        <!-- Mi CSS -->
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/CSS/style.css" media="screen" />

        <title>SmartEngine - Cesta</title>
    </head>
    <body>

        <!-- Si el usuario está registrado -->
        <c:if test="${sessionScope.usuario!=null}">
            <%@include file="../INC/headerRegistrado.inc" %>           
        </c:if>

        <!-- Si el usuario no está registrado -->
        <c:if test="${sessionScope.usuario==null}">
            <%@include file="../INC/headerAnonimo.inc" %>                
        </c:if>  

        <div class="container">


            <div class="card text-center mt-5">


                <div class="card-header">

                    <h3 class="card-title text-center">
                        <p>Cesta</p>
                    </h3>

                    <c:if test="${sessionScope.usuario!=null}">  
                        <c:set var="usuario" value='"${sessionScope.usuario}"'/>   
                        <h5 class="card-subtitle text-muted">${usuario.nombre} , estos son los productos que has elegido por ahora:</h5> 
                    </c:if> 

                    <c:if test="${sessionScope.usuario==null}">
                        <h5 class="card-subtitle text-muted">Usuario an&oacute;nimo, estos son los productos que has elegido por ahora:  </h5>              
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
                        <!-- DISEÑO TARJETAS PRODUCTO -->

                        <li class="list-group-item">
                        
                            <div class="row">

                                <div class="col-3">
                                    <img src="" alt="" class="img-fuid">
                                </div>

                                <div class="col-2">
                                    <p></p>
                                </div>

                            </div>
                            
                        </li>
                        
                    </ul

                   

                </div>


                <div class="card-footer">

                    <div class="row">
                        <div class="col-6">
                            <h5>Importe total: </h5>
                        </div>

                        <div class="col-6">

                            <div class="row">

                                <div class="col-6-lg col-12-sm">

                                    <form method="post" action="<%= request.getContextPath()%>/ControladorFinalizarCompra">

                                        <button type="submit" class="btn btn-outline-success" id="finCompra" value="fin" name="finCompra">Finalizar compra</button>
            
                                    </form>
                                </div>

                                <div class="col-6-lg col-12-sm">
                                    <a href="<%= request.getContextPath()%>/JSP/Tienda.jsp"><button type="submit" class="btn btn-outline-info" id="volver" name="finCompra">Seguir comprando</button></a>
                                </div>
                            </div>
                            

                            
                        </div>
                        
                    </div>
                    
                </div>

            </div>

        </div>



    </body>
</html>
