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
     * @return lineasPedido del usuario que tengan el en estado 'c'
     */
    public ArrayList<LineaCesta> getPedidos(int idUsuario);
    

    /**
     * cierra la conexion
     */
    public void closeConnection();  
}
