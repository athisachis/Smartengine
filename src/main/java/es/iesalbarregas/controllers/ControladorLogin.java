package es.iesalbarregas.controllers;

import es.iesalbarregas.DAO.MySQLUsuariosDAO;
import es.iesalbarregas.DAOFactory.DAOFactory;
import es.iesalbarregas.beans.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ana
 * Controlador que comprueba si el email introducido existe en la base de datos y coincide con la contraseña que se ha introducido, también dirige el fujo de la aplicación para, en caso de que las credenciales sean válidas, llevar al usuario hasta la tienda
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

        boolean usuarioRegistrado = false;

        DAOFactory DAOFactory = null;

        DAOFactory = DAOFactory.getDAOFactory(1);

        MySQLUsuariosDAO udao = new MySQLUsuariosDAO();

        Usuario usuarioBBDD = new Usuario();

        usuarioBBDD = udao.getUsuarioEmail(request.getParameter("email"));
        
        String email;
        String contrasenia;

        if (request.getParameter("email") != null) {

            email = request.getParameter("email");
            contrasenia = request.getParameter("password");

            if (usuarioBBDD.getEmail()!=null) {
                if (usuarioBBDD.getEmail().equals(email)&& usuarioBBDD.getContrasenia().equals(contrasenia)) {
                    
                    //Se guardan en sesion los datos del usuario
                    request.getSession().setAttribute("usuario", usuarioBBDD);
                    request.getRequestDispatcher("JSP/Tienda.jsp").forward(request, response);                               
                }else{
                    
                    String error = "Esta combinación de usuario y contraseña no existe en la base de datos";
                    request.setAttribute("error", error);
                    request.getRequestDispatcher("Login.jsp").forward(request, response); 
                }
            }            
          
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
