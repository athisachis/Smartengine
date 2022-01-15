<header>          
          <img src="<%= request.getContextPath()%>/IMG/logo.png" alt="logo" class="logo">
</header>

    <nav class="navbar navbar-expand-md navbar-dark bg-dark barraHerramientas">

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample04"
            aria-controls="navbarsExample04" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarsExample04">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="<%= request.getContextPath()%>/JSP/Tienda.jsp">Inicio <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Nosotros</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Contacto</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle"  id="dropdown04" data-toggle="dropdown"
                        aria-haspopup="true" aria-expanded="false">Categor&iacute;as</a>
                    <div class="dropdown-menu" aria-labelledby="dropdown04">

                        <a class="dropdown-item" id="0">Todos</a>

                        <c:forEach items="${categorias}" var="categoria">

                            <a class="dropdown-item" id="${categoria.idCategoria}">${categoria.nombre}</a>

                        </c:forEach>

                    </div>
                </li>
            </ul>
            <form class="form-inline my-2 my-md-0">
                <a href="<%= request.getContextPath()%>/JSP/Cesta.jsp"> <img src="<%= request.getContextPath()%>/IMG/carrito.png" alt="cesta"
                        class=" btn btn_login botonesNavbar"></a>
                <a href="<%= request.getContextPath()%>/ControladorPerfil"><img
                        src="<%= request.getContextPath()%>/IMG/user.png" alt="usuario"
                        class="btn btn_login botonesNavbar"></a>
            </form>
        </div>
    </nav>