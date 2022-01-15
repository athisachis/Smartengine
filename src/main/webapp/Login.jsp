<%-- 
    Document   : Login
    Created on : 26-dic-2021, 18:24:26
    Author     : Ana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../INC/metas.inc" %>
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/CSS/style.css" media="screen" />
        <!-- Notificaciones -->
        <link href="<%= request.getContextPath()%>/CSS/toastr.css" rel="stylesheet"/>
        <title>Login</title>
        <!--JQUERY-->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        
        <!-- FRAMEWORK BOOTSTRAP para el estilo de la pagina-->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        
        <!-- Los iconos tipo Solid de Fontawesome-->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/solid.css">
        <script src="https://use.fontawesome.com/releases/v5.0.7/js/all.js"></script>

        <!-- Nuestro css-->
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/CSS/index.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/CSS/style.css" media="screen" />
    </head>
    
    <body>

      <%@include file="../INC/header.inc" %>

      <main>

        <div class="container col-12">

            <div class="modal-dialog text-center">
                <div class="col-sm-8 main-section">
                    <div class="modal-content bg-dark col-12">
                        <div class="col-12 user-img">
                            <img src="<%= request.getContextPath()%>/IMG/login.png"/>
                        </div>
                        <form class="col-12"  action="ControladorLogin" method="post">
                            <div class="form-group" id="user-group">
                                <input type="email" class="form-control" placeholder="Email" name="email" id="email"/>
                            </div>
                            <div class="form-group" id="contrasena-group">
                                <input type="password" class="form-control" placeholder="Contraseña" name="password"  id="contrasenia"/>
                            </div>
                            <button type="submit" class="btn btn-light boton" id="botonLogin" ><i class="fas fa-sign-in-alt " ></i>  Acceder </button>
                        </form>
                        
                        <div class="errorMsg">
                            <c:set var="errorMsg" value='${requestScope["error"]}' />
                            <c:if test="${errorMsg!=null}">
                                <p><c:out value="${errorMsg}"/></p>
                            </c:if>
                        </div>
                        
                        <div class="col-12 forgot">
                            <a href="<%= request.getContextPath()%>/JSP/Registro.jsp">Registrarse</a> <br>
                            <a href="<%= request.getContextPath()%>/ControladorEntradaAnonimo">Acceder como usuario anónimo</a>
                        </div>
    
                    </div>
                </div>
            </div>

        </div>

        

        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <!--   Notificaciones -->        
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <!-- JS Propio -->
        <script src="<%= request.getContextPath()%>/JS/login.js"></script>

    </body>   
</html>