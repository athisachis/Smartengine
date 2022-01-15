package es.iesalbarregas.controllers;

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
@WebServlet(name = "ControladorAnadirCesta", urlPatterns = {"/ControladorAnadirCesta"})
public class ControladorAnadirCesta extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idProducto = Integer.parseInt(request.getParameter("botonCesta"));
        boolean yaEsta = false;
        ArrayList<LineaCesta> cesta = new ArrayList();
        Producto producto = new Producto();

        MySQLProductosDAO bbdd = new MySQLProductosDAO();

        if (request.getSession().getAttribute("cestaSmartEngine") != null) {

            cesta = (ArrayList<LineaCesta>) request.getSession().getAttribute("cestaSmartEngine");

            for (LineaCesta lineaCesta : cesta) {

                if (lineaCesta.getIdProducto() == idProducto) {
                    int cantidad = lineaCesta.getCantidad();
                    cantidad += 1;
                    lineaCesta.setCantidad(cantidad);
                    yaEsta = true;

                }

            }

            if (!yaEsta) {

                //Creo nueva linea producto y le pongo el idProducto
                LineaCesta nuevaLinea = new LineaCesta();
                nuevaLinea.setIdProducto(idProducto);

                producto = bbdd.getProductoId(idProducto);

                //Asignamos los atributos que faltan
                nuevaLinea.setNombre(producto.getNombre());
                nuevaLinea.setMarca(producto.getMarca());
                nuevaLinea.setImagen(producto.getImagen());
                nuevaLinea.setCantidad(1);
                nuevaLinea.setPrecioUnitario(producto.getPrecio());

                cesta.add(nuevaLinea);

            }
        } else {

            //Creo nueva linea producto y le pongo el idProducto
            LineaCesta nuevaLinea = new LineaCesta();
            nuevaLinea.setIdProducto(idProducto);

            producto = bbdd.getProductoId(idProducto);

            //Asignamos los atributos que faltan
            nuevaLinea.setNombre(producto.getNombre());
            nuevaLinea.setMarca(producto.getMarca());
            nuevaLinea.setImagen(producto.getImagen());
            nuevaLinea.setCantidad(1);
            nuevaLinea.setPrecioUnitario(producto.getPrecio());

            cesta.add(nuevaLinea);

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

            //Para sobreescribir la cookie o crearla
            Cookie cookieTienda = new Cookie("cestaSmartengine", URLEncoder.encode(contenidoCookie, "UTF-8"));
            // Añadimos la caducidad de dos días
            cookieTienda.setMaxAge(172800);
            // Añadimos la cookie a la respuesta
            response.addCookie(cookieTienda);

        }

        //Redirigimos a la tienda
        request.getRequestDispatcher("/JSP/Categoria.jsp").forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
