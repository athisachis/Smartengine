package es.iesalbarregas.controllers;

import es.iesalbarregas.DAO.MySQLProductosDAO;
import es.iesalbarregas.beans.LineaCesta;
import es.iesalbarregas.beans.Producto;
import java.io.IOException;
import java.net.URLDecoder;
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
@WebServlet(name = "ControladorEntradaAnonimo", urlPatterns = {"/ControladorEntradaAnonimo"})
public class ControladorEntradaAnonimo extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Buscamos si ya existe una cookie que contenga un carrito
        Cookie cookie = null;

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("cestaSmartengine")) {
                    cookie = cookies[i];
                    break;
                }
            }

            if (cookie != null) {

                MySQLProductosDAO bbdd = new MySQLProductosDAO();

                //Traducimos el valor de la cookie en objetos
                String contenidoCookie = URLDecoder.decode(cookie.getValue(), "UTF-8");

                //Hacemos el split en ? que es el separador que he puesto entre los diferentes objetos
                String objetosCookie[] = contenidoCookie.split("<=>");

                //Creamos la coleccion donde ira la cesta
                ArrayList<LineaCesta> cesta = new ArrayList();

                //Recorremos el array de los objetos
                for (String objetoCookie : objetosCookie) {

                    //Creamos otro array con los atributos que he separado con el separador #
                    String atributos[] = objetoCookie.split("#");

                    if (!atributos[0].equals("")) {

                        //Creamos objeto lineaCesta
                        LineaCesta lineaCesta = new LineaCesta();

                        //Metemos en cada objeto lineaCesta los distintos atributos
                        lineaCesta.setIdProducto(Integer.parseInt(atributos[0]));
                        lineaCesta.setCantidad(Integer.parseInt(atributos[1]));

                        //Creamos objeto producto con la información del producto proveniente de la bbdd
                        Producto producto = bbdd.getProductoId(lineaCesta.getIdProducto());

                        //Llenamos los atributos de lineaCesta
                        lineaCesta.setNombre(producto.getNombre());
                        lineaCesta.setMarca(producto.getMarca());
                        lineaCesta.setImagen(producto.getImagen());
                        lineaCesta.setPrecioUnitario(producto.getPrecio());

                        //Añado el objeto lineaCesta al arrayList
                        cesta.add(lineaCesta);
                    }

                }

                //Añadimos la colección a la sesión
                request.getSession().setAttribute("cestaSmartengine", cesta);

            }
        }

        //Redirigimos a la tienda
        request.getRequestDispatcher("/JSP/Tienda.jsp").forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
