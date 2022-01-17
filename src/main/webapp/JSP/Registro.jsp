<%-- 
    Document   : Registro
    Created on : 26-dic-2021, 18:39:12
    Author     : Ana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../INC/metas.inc" %>
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/CSS/style.css" media="screen" />
        <title>Registro</title>
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
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/CSS/registro.css" media="screen" />
    </head>
    
    <body>

      <%@include file="../INC/header.inc" %>

      <main>

        <div class="container col-12">

            <div class="modal-dialog text-center">
                <div class="col-sm-8 main-section">
                    <div class="modal-content bg-dark col-12">
                        <div class="col-12 user-img">
                            <img src="<%= request.getContextPath()%>/IMG/registro.png"/>
                        </div>
                        <form class="col-12"  action="<%= request.getContextPath()%>/ControladorRegistro" method="post" id="user-form" enctype='multipart/form-data'>
                            <div class="form-group" id="user-group">
                                <input type="email" class="form-control inputRegistro" placeholder="Email*" name="email" id="email" maxlength="50" required/>
                                <small class="error-text" id="errorMail">El formato del email es incorrecto</small>
                                <small class="error-text" id="errorMailExiste">Este email ya está registrado</small>
                            </div>
                            <div class="form-group" id="contrasena-group">
                                <input type="password" class="form-control inputRegistro" placeholder="Contraseña*" name="contrasenia" id="contrasenia" maxlength="30" required/>
                            </div>
                            <div class="form-group" id="contrasena-group">
                                <input type="password" class="form-control inputRegistro" placeholder=" Repetir contraseña*" name="contrasenia2" id="contrasenia2" maxlength="30" required/>
                                <small class="error-text" id="errorContrasenia">Las contraseñas deben coincidir</small>
                            </div>
                            <div class="form-group" >
                                <input type="text" class="form-control inputRegistro" placeholder="Nombre*" name="nombre" maxlength="20" required/>
                            </div>
                            <div class="form-group" >
                                <input type="text" class="form-control" placeholder="Apellidos*" name="apellidos" maxlength="30" required/>
                            </div>
                            <div class="form-group" >
                                <input type="text" class="form-control" placeholder="NIF (sin letra)*" name="nif" maxlength="9" required/>
                                <small class="error-text" id="errorNif">Se deben introducir 8 números sin la letra</small>
                            </div>
                            <div class="form-group" >
                                <input type="tel" class="form-control" placeholder="Teléfono" name="telefono" maxlength="9"/>
                                <small class="error-text" id="errorTlf">Número de teléfono no válido</small>
                            </div>
                            <div class="form-group" >
                                <input type="text" class="form-control" placeholder="Dirección*" name="direccion" maxlength="40" required/>
                            </div>
                            <div class="form-group" >
                                <input type="text" class="form-control" placeholder="Código postal*" name="codPostal" maxlength="30" maxlength="5" required/>
                                <small class="error-text" id="errorCP">Código postal no válido</small>
                            </div>
                            <div class="form-group" >
                                <input type="text" class="form-control" placeholder="Localidad*" name="localidad" maxlength="40" required/>
                            </div>
                            <div class="form-group" >
                                <input type="text" class="form-control" placeholder="Provincia*" name="provincia" maxlength="30" required/>
                            </div>
                            <div class="form-group" >
                                <input type="file" class="form-control" placeholder="Avatar" name="avatar" accept="image/png, image/jpeg"/>
                            </div>



                            <button type="submit" class="btn btn-light boton" name="boton" id="botonRegistro"><i class="fas fa-sign-in-alt"></i>Registrarse</button>
                        </form>
                        <div class="col-12 forgot">
                            <a href="<%= request.getContextPath()%>/Login.jsp">Ya tengo cuenta</a>
                            
                        </div>

                        <div id="ohsnap"></div>
    
                    </div>
                </div>
            </div>

        </div>

        

        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script
        src="https://code.jquery.com/jquery-3.6.0.min.js"
        integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
        crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <!--   Notificaciones -->        
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <!-- <script src="<%= request.getContextPath()%>/JS/ohsnap.js"></script> -->
        <!-- JS Propio -->
        <!-- <script src="<%= request.getContextPath()%>/JS/registro.js"></script> -->
        <script src="<%= request.getContextPath()%>/JS/registro.js"></script>

    </body>   
</html>