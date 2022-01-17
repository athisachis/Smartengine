package es.iesalbarregas.controllers;

import es.iesalbarregas.DAO.MySQLPedidosDAO;
import es.iesalbarregas.DAO.MySQLUsuariosDAO;
import es.iesalbarregas.DAOFactory.DAOFactory;
import es.iesalbarregas.beans.LineaCesta;
import es.iesalbarregas.beans.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ana 
 * Controlador que comprueba si el email introducido existe en la
 * base de datos y coincide con la contraseña que se ha introducido, también
 * dirige el fujo de la aplicación para, en caso de que las credenciales sean
 * válidas, llevar al usuario hasta la tienda
 */
@WebServlet(name = "ControladorLogin", urlPatterns = {"/ControladorLogin"})
public class ControladorLogin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DAOFactory DAOFactory = null;

        DAOFactory = DAOFactory.getDAOFactory(1);

        MySQLUsuariosDAO udao = new MySQLUsuariosDAO();

        Usuario usuarioBBDD = new Usuario();
       
        if (request.getParameter("email") != null) {

            String email = request.getParameter("email");
            String contrasenia = request.getParameter("password");

            usuarioBBDD = udao.getUsuarioEmail(email, contrasenia);

            if (usuarioBBDD.getEmail() != null) {

                udao.updateUltimoAcceso(usuarioBBDD.getIdUsuario());
                //Se guardan en sesion los datos del usuario
                request.getSession().setAttribute("usuario", usuarioBBDD);
                request.getRequestDispatcher("JSP/Tienda.jsp").forward(request, response);
                
                //Cargar carrito
                
                MySQLPedidosDAO pdao = new MySQLPedidosDAO();
                
                ArrayList<LineaCesta> cesta = new ArrayList();
                
                cesta = pdao.getPedidos(usuarioBBDD.getIdUsuario());
                
                if (!cesta.isEmpty()) {
                    
                    request.getSession().setAttribute("cestaSmartengine", cesta);
                    
                }
                
                
            } else {
                String error = "Esta combinación de usuario y contraseña no existe en la base de datos";
                request.setAttribute("error", error);
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }

        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
