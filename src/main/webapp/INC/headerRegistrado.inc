    <header>
        <img src="<%= request.getContextPath()%>/IMG/logo.png" alt="logo" class="logo">

        <nav class="navbar navbar-expand-md navbar-dark">
        
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">

              <a href="#"> <img src="<%= request.getContextPath()%>/IMG/carrito.png" alt="carrito" class=" btn btn_login"></a>
              <a href="<%= request.getContextPath()%>/ControladorPerfil"><img src="<%= request.getContextPath()%>/IMG/user.png" alt="usuario" class="btn btn_login"></a>
              <a href="<%= request.getContextPath()%>/ControladorCerrarSesion"><img src="<%= request.getContextPath()%>/IMG/exit.png" alt="salir" class="btn btn_login"></a>
            </div>
          </nav>
    </header>