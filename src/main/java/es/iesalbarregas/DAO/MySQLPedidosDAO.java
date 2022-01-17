package es.iesalbarregas.DAO;

import es.iesalbarregas.beans.LineaCesta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ana
 */
public class MySQLPedidosDAO implements IPedidosDAO {

    @Override
    public ArrayList<LineaCesta> getPedidos(int idUsuario) {

        ArrayList<LineaCesta> cesta = new ArrayList();

        String consulta = "SELECT pe.idPedido, lp.idLinea, lp.idProducto, pr.Nombre, pr.marca, pr.imagen, lp.cantidad, pr.precio FROM pedidos as pe INNER JOIN lineaspedidos as lp ON pe.idPedido = lp.idPedido inner join productos as pr on lp.idProducto=pr.idProducto where idUsuario=? and pe.estado='c';";

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
    public void anadirProducto(LineaCesta producto) {

        Connection conex = ConnectionFactory.abrirConexion();

        String consulta = "insert into lineaspedidos (idPedido, idProducto, cantidad) values (?, ?, ?);";

        try {

            conex.setAutoCommit(false);

            PreparedStatement preparada = conex.prepareStatement(consulta);

            preparada.setInt(1, producto.getIdPedido());
            preparada.setInt(2, producto.getIdProducto());
            preparada.setInt(3, producto.getCantidad());

            preparada.execute();

            preparada.close();

            conex.commit();

        } catch (SQLException e) {
            System.out.println("Problema con la base de datos");
            try {
                conex.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLUsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            this.closeConnection();

        }

    }

    @Override
    public int anadirPedido(int idUsuario) {

        Connection conex = ConnectionFactory.abrirConexion();

        String consulta = "insert into pedidos (idUsuario) values (?);";

        try {

            conex.setAutoCommit(false);

            PreparedStatement preparada = conex.prepareStatement(consulta);

            preparada.setInt(1, idUsuario);

            preparada.execute();

            preparada.close();

            conex.commit();

        } catch (SQLException e) {
            System.out.println("Problema con la base de datos");
            try {
                conex.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLUsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            this.closeConnection();

        }

        //SEGUNDA CONSULTA
        String consulta2 = "SELECT MAX(idPedido) from pedidos;";

        int idPedido = 0;

        try {
            Statement sentencia = ConnectionFactory.abrirConexion().createStatement();
            try {
                ResultSet resultado = sentencia.executeQuery(consulta2);

                while (resultado.next()) {

                    idPedido = resultado.getInt(1);
                }
            } catch (SQLException e) {
                System.out.println("Error al ejecutar la sentencia");
            }
        } catch (SQLException e) {
            System.out.println("Error al abrir conexion con la BBDD");
        } finally {
            this.closeConnection();
        }

        return idPedido;
    }

    @Override
    public void closeConnection() {
        ConnectionFactory.cerrarConexion();
    }

}
