<%-- 
    Document   : index
    Created on : 26-dic-2021, 18:24:26
    Author     : Ana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../INC/metas.inc" %>
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/CSS/style.css" media="screen" />
        <title>Login</title>
    </head>
    
    <body>

      <header>
          <img src="<%= request.getContextPath()%>/IMG/logo.png" alt="logo" class="logo">
      </header>

      <main>

        <div class="elemento">

            <h2>Accede con tu correo:</h2> <br>
          <form action="ControladorLogin" method="post">

            <input type="email" name="usuario" placeholder="Correo electrónico: " class="inputText"> <br>
            <input type="password" name="contrasenia" placeholder="Contraseña: " class="inputText"> <br> 

            <input type="submit" value="Acceder" name="boton" class="button">

          </form>

        </div>

        <div class="elemento">

          <h2>Tambi&eacute;n puedes registrarte si a&uacute;n no tienes cuenta o acceder con un usuario an&oacute;nimo:</h2>
          
            <form action="ControladorRegistro" method="post">

              <input type="submit" value="Registro" name="boton" class="button">

            </form>

            <form action="ControladorAnonimo" method="post">

                <input type="submit" value="Usuario sin registro" name="boton" class="button">

            </form>

        </div>

        <footer>

          <h3>Ana Larios Vettoretti DAW2 &copy;</h3>

        </footer>

      </main>

    </body>   
</html>
