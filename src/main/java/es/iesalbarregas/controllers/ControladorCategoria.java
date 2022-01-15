package es.iesalbarregas.controllers;

import es.iesalbarregas.beans.Producto;
import es.iesalbarregas.DAO.MySQLProductosDAO;
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

        int idCategoria = 0;

        if (request.getAttribute("idCategoria") != null) {

            idCategoria = Integer.parseInt((String) request.getAttribute("idCategoria"));

        } else {
            if (request.getParameter("categoria") != null) {
                idCategoria = Integer.parseInt(request.getParameter("categoria"));
            }
        }

        ArrayList<Producto> productos = new ArrayList();
        MySQLProductosDAO bbdd = new MySQLProductosDAO();

        if (idCategoria != 0) {
            productos = bbdd.getProductosCategoria(idCategoria);

            if (!productos.isEmpty()) {
                request.setAttribute("productosCategoria", productos);
            }
        } else {

            productos = bbdd.getProductos();
            if (!productos.isEmpty()) {
                request.setAttribute("productosCategoria", productos);                  
            }
        }
        
//        request.getSession().setAttribute("categoriaElegida", productos);

        request.getRequestDispatcher("/JSP/Categoria.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
