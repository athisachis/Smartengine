package es.iesalbarregas.events;

import es.iesalbarregas.DAO.MySQLCategoriasDAO;
import es.iesalbarregas.beans.Categoria;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author Ana
 */
public class EventoInicio implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        // Creamos un objeto contexto
        ServletContext contexto = sce.getServletContext();
        // Accedemos a la base de datos para obtener los datos necesarios
        MySQLCategoriasDAO baseDatos = new MySQLCategoriasDAO();
        ArrayList<Categoria> categorias = baseDatos.getCategorias();
        
        // Guardamos el atributo de aplicación con lo obtenido en la BD
        synchronized (contexto) {
            contexto.setAttribute("categorias", categorias);
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}
