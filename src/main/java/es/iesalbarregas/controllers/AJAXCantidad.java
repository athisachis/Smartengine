package es.iesalbarregas.controllers;

import es.iesalbarregas.DAO.MySQLPedidosDAO;
import es.iesalbarregas.DAO.MySQLProductosDAO;
import es.iesalbarregas.beans.LineaCesta;
import es.iesalbarregas.beans.Producto;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ana
 */
@WebServlet(name = "AJAXCantidad", urlPatterns = {"/AJAXCantidad"})
public class AJAXCantidad extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<LineaCesta> cesta = new ArrayList();

        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        
        int idLinea = 0;

        MySQLProductosDAO bbdd = new MySQLProductosDAO();
        cesta = (ArrayList<LineaCesta>) request.getSession().getAttribute("cestaSmartengine");

        for (LineaCesta lineaCesta : cesta) {

            if (lineaCesta.getIdProducto() == idProducto) {

                lineaCesta.setCantidad(cantidad);
                
                idLinea = lineaCesta.getIdLinea();

            }

        }
        
        request.getSession().setAttribute("cestaSmartengine", cesta);

        //GESTION COOKIE
        if (request.getSession().getAttribute("usuario") == null) {

            //Creamos el contenido de la cookie
            String contenidoCookie = "";
            int idProductoLinea;
            int cantidadLinea;

            for (LineaCesta lineaCesta : cesta) {

                idProductoLinea = lineaCesta.getIdProducto();
                cantidadLinea = lineaCesta.getCantidad();

                contenidoCookie += "<=>" + idProductoLinea + "#" + cantidadLinea;

            }

            //Para sobreescribir la cookie
            Cookie cookieTienda = new Cookie("cestaSmartengine", URLEncoder.encode(contenidoCookie, "UTF-8"));
            // Añadimos la caducidad de dos días
            cookieTienda.setMaxAge(172800);
            // Añadimos la cookie a la respuesta
            response.addCookie(cookieTienda);
        }else{
            
            //USUARIOS REGISTRADOS
            MySQLPedidosDAO pdao = new MySQLPedidosDAO();
            
            pdao.updateCantidad(idLinea, cantidad);
            
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
