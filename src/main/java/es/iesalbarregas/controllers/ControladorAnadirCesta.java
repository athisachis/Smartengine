package es.iesalbarregas.controllers;

import es.iesalbarregas.DAO.MySQLPedidosDAO;
import es.iesalbarregas.DAO.MySQLProductosDAO;
import es.iesalbarregas.beans.LineaCesta;
import es.iesalbarregas.beans.Producto;
import es.iesalbarregas.beans.Usuario;
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
        MySQLPedidosDAO pdao = new MySQLPedidosDAO();

        if (request.getSession().getAttribute("cestaSmartengine") != null) {

            cesta = (ArrayList<LineaCesta>) request.getSession().getAttribute("cestaSmartengine");

            for (LineaCesta lineaCesta : cesta) {

                if (lineaCesta.getIdProducto() == idProducto) {
                    int cantidad = lineaCesta.getCantidad();
                    cantidad += 1;
                    lineaCesta.setCantidad(cantidad);
                    yaEsta = true;

                    //BBDD
                    if (request.getSession().getAttribute("usuario") != null) {

                        int idLinea = lineaCesta.getIdLinea();
                        //update cantidad                        
                        pdao.updateCantidad(idLinea, cantidad);

                    }

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

                if (request.getSession().getAttribute("usuario") != null) {

                    LineaCesta cualquiera = cesta.get(0);

                    int idPedido = cualquiera.getIdPedido();

                    nuevaLinea.setIdPedido(idPedido);

                    int idNuevaLinea = pdao.anadirProducto(nuevaLinea);

                    nuevaLinea.setIdPedido(idPedido);

                    nuevaLinea.setIdLinea(idNuevaLinea);

                }

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

            //PARA AÑADIR A LA BBDD
            if (request.getSession().getAttribute("usuario") != null) {

                Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

                int idPedidoNuevo = pdao.anadirPedido(usuario.getIdUsuario());

                nuevaLinea.setIdPedido(idPedidoNuevo);

                int idNuevaLinea = pdao.anadirProducto(nuevaLinea);

                nuevaLinea.setIdLinea(idNuevaLinea);

            }

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

        if (request.getSession().getAttribute("categoriaElegida") == null) {
            //Redirigimos a la tienda
            request.getRequestDispatcher("/JSP/Tienda.jsp").forward(request, response);
        } else {
            //Redirigimos a la categoria
            request.getRequestDispatcher("/JSP/Categoria.jsp").forward(request, response);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
