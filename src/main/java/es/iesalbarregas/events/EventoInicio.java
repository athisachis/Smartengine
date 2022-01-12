package es.iesalbarregas.events;

import es.iesalbarregas.DAO.MySQLCategoriasDAO;
import es.iesalbarregas.DAO.MySQLProductosDAO;
import es.iesalbarregas.beans.Categoria;
import es.iesalbarregas.beans.Producto;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author Ana
 */
@WebListener
public class EventoInicio implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        // Creamos un objeto contexto
        ServletContext contexto = sce.getServletContext();
        // Accedemos a la base de datos para obtener los datos necesarios
        MySQLCategoriasDAO baseDatos = new MySQLCategoriasDAO();
        ArrayList<Categoria> categorias = baseDatos.getCategorias();
        
        //Accedemos a la bbdd para ver el producto más vendido
        MySQLProductosDAO bbdd = new MySQLProductosDAO();
        Producto masVendido = bbdd.getProductoMasVendido();
        
        // Guardamos el atributo de aplicación con lo obtenido en la BD
        synchronized (contexto) {
            contexto.setAttribute("categorias", categorias);
            contexto.setAttribute("masVendido", masVendido);
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}
