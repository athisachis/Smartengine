package es.iesalbarregas.DAO;

import es.iesalbarregas.beans.Categoria;
import es.iesalbarregas.beans.LineaCesta;
import java.util.ArrayList;

/**
 *
 * @author Ana
 */
public interface IPedidosDAO {
    
    /**
     *
     * @param idUsuario
     * @return lineasPedido del usuario que tengan el en estado 'c'
     */
    public ArrayList<LineaCesta> getPedidos(int idUsuario);
    
    /**
     *
     * @param producto
     */
    public void anadirProducto(LineaCesta producto);
    
    /**
     *
     * @param idPedido
     * @return devuelve el idPedido que se le ha asignado para poderselo asignar a las lineaspedido
     */
    public int anadirPedido(int idPedido);
    
  
    /**
     * cierra la conexion
     */
    public void closeConnection();  
}
