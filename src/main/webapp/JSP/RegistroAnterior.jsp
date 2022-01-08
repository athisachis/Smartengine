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
        <title>Registro</title>
    </head>
    
    <body>

      <%@include file="../INC/header.inc" %>

      <main>

        <div class="elemento">

            <h2>Reg&iacute;strate:</h2>
          <form action="ControladorRegistro" method="post">

              <input type="email" name="usuario" placeholder="Correo electrónico: " class="inputText"> <br>
              <input type="password" name="contrasenia" placeholder="Contraseña: " class="inputText"> <br> 

            <input type="submit" value="Registrarse" name="boton" class="button">

          </form>

        </div>

        <div class="elemento">
          
          <form action="ControladorRegistro" method="post">

            <input type="submit" value="Login" name="boton" class="button">

          </form>



        </div>

        <footer>

          <h3>Ana Larios Vettoretti DAW2 &copy;</h3>

        </footer>

      </main>

    </body>   
</html>
