package es.iesalbarregas.DAO;

import es.iesalbarregas.beans.Categoria;
import es.iesalbarregas.beans.Producto;
import java.util.ArrayList;

/**
 *
 * @author Ana
 */
public interface ICategoriasDAO {
    
    /**
     *
     * @return todos los artículos de la bbdd
     */
    public ArrayList<Categoria> getCategorias();
    

    /**
     * cierra la conexion
     */
    public void closeConnection();  
}
