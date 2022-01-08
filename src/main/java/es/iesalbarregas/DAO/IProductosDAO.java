package es.iesalbarregas.DAO;

import es.iesalbarregas.beans.Categoria;
import es.iesalbarregas.beans.Producto;
import java.util.ArrayList;

/**
 *
 * @author Ana
 */
public interface IProductosDAO {
    
    /**
     *
     * @return todos los artículos de la bbdd
     */
    public ArrayList<Producto> getProductos();
    
    /**
     *
     * @return todos los artículos de la bbdd que tengan esa categoria
     */
    public ArrayList<Producto> getProductosCategoria(Categoria categoria);
    
    /**
     * cierra la conexion
     */
    public void closeConnection();  
}
