package es.iesalbarregas.models;

import es.iesalbarregas.DAO.MySQLProductosDAO;
import es.iesalbarregas.beans.LineaCesta;
import es.iesalbarregas.beans.Producto;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Ana
 */
public class BuscarCookie {
    
    public BuscarCookie(HttpServletRequest request) throws UnsupportedEncodingException{
        
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
                String ObjetosCookie[] = contenidoCookie.split("<=>");

                //Creamos la coleccion donde ira la cesta
                ArrayList<LineaCesta> cesta = new ArrayList();

                //Creamos objeto libro
                LineaCesta lineaCesta = new LineaCesta();

                //Recorremos el array de los objetos
                for (int j = 0; j < ObjetosCookie.length; j++) {

                    //Creamos otro array con los atributos que he separado con el separador #
                    String atributos[] = ObjetosCookie[j].split("#");

                    //Metemos en cada objeto lineaCesta los distintos atributos
                    lineaCesta.setIdProducto(Integer.parseInt(atributos[0]));
                    lineaCesta.setCantidad(Integer.parseInt(atributos[1]));

                    Producto producto = bbdd.getProductoId(lineaCesta.getIdProducto());
                    
                    lineaCesta.setNombre(producto.getNombre());
                    lineaCesta.setImagen(producto.getImagen());
                    lineaCesta.setPrecioUnitario(producto.getPrecio());
                    
                    //Añado el objeto lineaCesta al arrayList
                    cesta.add(lineaCesta);
                    
                }
                
                //Añadimos la colección a la sesión
                request.getSession().setAttribute("cestaSmartEngine", cesta);
                
            }
        }
        
    }
    
}
