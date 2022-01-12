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
            
            <!-- Si el usuario está registrado -->
            <!-- <c:if test="${sessionScope.usuario!=null}">
                <%@include file="../INC/headerRegistrado.inc" %>                
            </c:if> -->

            <!-- Si el usuario no está registrado -->
            <!-- <c:if test="${sessionScope.usuario==null}">
                <%@include file="../INC/headerAnonimo.inc" %>                
            </c:if> -->
        </header>
    </body>
</html>
