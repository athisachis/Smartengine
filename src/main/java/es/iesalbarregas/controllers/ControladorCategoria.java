package es.iesalbarregas.controllers;

import es.iesalbarregas.beans.Producto;
import es.iesalbarregas.DAO.MySQLProductosDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ana
 */
@WebServlet(name = "ControladorCategoria", urlPatterns = {"/ControladorCategoria"})
public class ControladorCategoria extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int idCategoria = Integer.parseInt(request.getParameter("categoria"));
        
        ArrayList <Producto> productos = new ArrayList();
        MySQLProductosDAO bbdd = new MySQLProductosDAO();
        productos = bbdd.getProductosCategoria(idCategoria);
        
        request.setAttribute("productosCategoria", productos);
        
        request.getRequestDispatcher("/JSP/Categoria.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
