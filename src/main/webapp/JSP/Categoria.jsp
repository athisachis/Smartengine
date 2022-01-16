<%-- 
    Document   : Categoria
    Created on : 12-ene-2022, 12:47:40
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
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/CSS/girarCards.css" media="screen" />

        <title>SmartEngine - Categoria</title>
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

            
            <c:set var="productos" value='${sessionScope.categoriaElegida}' />

            <c:if test="${productos.size()==0}">

                <br> <br>
                <div class="alert alert-dark" role="alert">
                    No hay productos de esta categor&iacute;a.
                  </div>
            </c:if>
                
                
            
            <div class="card-deck mt-5">  
                


                <c:if test="${productos!=null}">
                      
                    <c:forEach items="${productos}" var="producto">

                        <div class="col-lg-4 col-sm-6 contenedorCard">


                                <div class="card text-center" style="width: 18rem;" id="${producto.idProducto}">
                                    <div class="embed-responsive embed-responsive-1by1">
                                        <img class="card-img-top embed-responsive-item" src="<%= request.getContextPath()%>/IMG/productos/${producto.imagen}.jpg" alt="Imagen categoria">
                                    </div>
                                    
                                    <div class="card-body">
                                        <p class="card-text"> ${producto.nombre} </p>
                                        <h6 class="card-subtitle mb-2 text-muted">${producto.precio}€</h6>
                                        
                                        <form method="post" action="<%= request.getContextPath()%>/ControladorAnadirCesta">
    
                                            <button type="submit" class="btn btn-outline-dark" id="anadirCesta" value="${producto.idProducto}" name="botonCesta">Añadir a cesta</button>
    
                                        </form>

                                        <button class="btn btn-outline-dark" id="masInfo" name="botonCesta" data-toggle="modal" data-target="#exampleModalScrollable${contador}">Más información</button>

                                        <div class="modal " id="exampleModalScrollable${contador}" tabindex="-1" role="dialog" aria-labelledby="exampleModalScrollableLabel" aria-hidden="true">
                                            <div class="modal-dialog modal-dialog-scrollable" role="document">
                                              <div class="modal-content bg-dark text-light">
                                                <div class="modal-header">
                                                  <h5 class="modal-title text-center" id="">${producto.nombre}</h5>
                                                  
                                                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                  </button>
                                                </div>
                                                <div class="modal-body">
                                                    <h5 class="text-center">${producto.marca}</h5>
                                                    <p class="text-left">${producto.descripcion}</p>
                                                    <h5 class="text-center">Precio: ${producto.precio}€</h5>
                                                </div>
                                                <div class="modal-footer">
                                                  <button type="button" class="btn btn-outline-info" data-dismiss="modal">Cerrar</button>
                                                  <form method="post" action="<%= request.getContextPath()%>/ControladorAnadirCesta">    
                                                    <button type="submit" class="btn btn-outline-info" id="anadirCesta" value="${producto.idProducto}" name="botonCesta">Añadir a cesta</button>
                                                      
                                                </form>
                                                </div>
                                              </div>
                                            </div>
                                        </div>
                                        
                                    </div>
                                </div>

                                <c:set var="contador" value='${contador+1}' />
                            

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
        <script src="<%= request.getContextPath()%>/JS/categorias.js"></script>
    </body>
</html>