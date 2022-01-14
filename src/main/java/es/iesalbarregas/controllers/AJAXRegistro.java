package es.iesalbarregas.controllers;

import es.iesalbarregas.DAO.MySQLUsuariosDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author error
 */
@WebServlet(name = "AJAXRegistro", urlPatterns = {"/AJAXRegistro"})
public class AJAXRegistro extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * doPost de AJAXRegistro
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * Comprueba si el email introducido existe ya en la BBDD
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Creamos objeto JSON
        JSONObject json = new JSONObject();
        
        //Creamos el boolean que es lo que vamos a devolver
        boolean yaRegistrado= false;
        
        MySQLUsuariosDAO baseDatos = new MySQLUsuariosDAO();
        
        String email = request.getParameter("email");
        
        //Ejecuta el método comprobar emial con el email que se ha introducido en formulario
        yaRegistrado = baseDatos.comprobarEmail(email);
        
        json.put("yaRegistrado", yaRegistrado);
        
        response.setHeader("header", "Content-Type: application/json; charset=utf-8");
        response.setContentType("application/json");
        response.getWriter().print(json);
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
