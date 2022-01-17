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
     * @return idLinea que se le ha asignado a esa LineaPedido
     */
    public int anadirProducto(LineaCesta producto);

    /**
     *
     * @param idPedido
     * @return devuelve el idPedido que se le ha asignado para poderselo asignar
     * a las lineaspedido
     */
    public int anadirPedido(int idPedido);

    /**
     *
     * @param idLinea
     * @param cantidad
     */
    public void updateCantidad(int idLinea, int cantidad);

    /**
     *
     * @param idPedido
     */
    public void eliminarPedido(int idPedido);

    /**
     *
     * @param idPedido
     */
    public void finalizarPedido(int idPedido);

    /**
     * cierra la conexion
     */
    public void closeConnection();
}
