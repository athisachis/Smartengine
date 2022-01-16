package es.iesalbarregas.DAO;

import es.iesalbarregas.beans.LineaCesta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Ana
 */
public class MySQLPedidosDAO implements IPedidosDAO {

    @Override
    public ArrayList<LineaCesta> getPedidos(int idUsuario) {

        ArrayList<LineaCesta> cesta = new ArrayList();

        String consulta = "SELECT pe.idPedido, lp.idLinea, lp.idProducto, pr.Nombre, pr.marca, pr.imagen, lp.cantidad, pr.precio FROM pedidos as pe INNER JOIN lineaspedidos as lp ON pe.idPedido = lp.idPedido inner join productos as pr lp.idProducto=pr.idProducto where idUsuario=? and pe.estado='c';";

        try {

            PreparedStatement preparada = ConnectionFactory.abrirConexion().prepareStatement(consulta);
            preparada.setInt(1, idUsuario);

            ResultSet resultado = preparada.executeQuery();

            while (resultado.next()) {
                LineaCesta lineaCesta = new LineaCesta();
                lineaCesta.setIdProducto(resultado.getInt("IdProducto"));
                lineaCesta.setIdPedido(resultado.getInt("IdPedido"));
                lineaCesta.setIdLinea(resultado.getInt("IdLinea"));
                lineaCesta.setNombre(resultado.getString("Nombre"));
                lineaCesta.setImagen(resultado.getString("Imagen"));
                lineaCesta.setMarca(resultado.getString("marca"));
                lineaCesta.setCantidad(resultado.getInt("cantidad"));
                lineaCesta.setPrecioUnitario(resultado.getDouble("precio"));

                cesta.add(lineaCesta);
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la sentencia");
        } finally {
            this.closeConnection();
        }

        return cesta;
    }

    @Override
    public void closeConnection() {
        ConnectionFactory.cerrarConexion();
    }

}
