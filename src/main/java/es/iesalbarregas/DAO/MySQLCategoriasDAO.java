package es.iesalbarregas.DAO;

import es.iesalbarregas.beans.Categoria;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Ana
 */
public class MySQLCategoriasDAO implements ICategoriasDAO {

    @Override
    public ArrayList<Categoria> getCategorias() {

        ArrayList<Categoria> categorias = new ArrayList();

        String consulta = "select * from categorias; ";

        try {
            Statement sentencia = ConnectionFactory.abrirConexion().createStatement();
            try {
                ResultSet resultado = sentencia.executeQuery(consulta);

                while (resultado.next()) {
                    Categoria categoria = new Categoria();
                    categoria.setIdCategoria(resultado.getInt("IdCategoria"));
                    categoria.setNombre(resultado.getString("Nombre"));
                    categoria.setImagen(resultado.getString("Imagen"));

                    categorias.add(categoria);
                }
            } catch (SQLException e) {
                System.out.println("Error al ejecutar la sentencia");
            }
        } catch (SQLException e) {
            System.out.println("Error al abrir conexion con la BBDD");
        } finally {
            this.closeConnection();
        }

        return categorias;

    }

    @Override
    public void closeConnection() {
        ConnectionFactory.cerrarConexion();
    }

}
