package es.iesalbarregas.DAO;

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
    public ArrayList<Producto> getProductosCategoria(int idCategoria);
    
    /**
     *
     * @return objeto Producto del producto que más se ha vendido
     */
    public Producto getProductoMasVendido();
    
    /**
     *
     * @param idProducto
     * @return producto de la bbdd que tiene ese id
     */
    public Producto getProductoId(int idProducto);
    
    /**
     * cierra la conexion
     */
    public void closeConnection();  
}
