package es.iesalbarregas.DAO;

import es.iesalbarregas.beans.Producto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Ana
 */
public class MySQLProductosDAO implements IProductosDAO {

    @Override
    public ArrayList<Producto> getProductos() {

        ArrayList<Producto> productos = new ArrayList<>();
        String consulta = "select * from productos";

        try {
            Statement sentencia = ConnectionFactory.abrirConexion().createStatement();
            try {
                ResultSet resultado = sentencia.executeQuery(consulta);

                while (resultado.next()) {
                    Producto producto = new Producto();
                    producto.setIdProducto(resultado.getInt("IdProducto"));
                    producto.setIdCategoria(resultado.getInt("IdCategoria"));
                    producto.setNombre(resultado.getString("Nombre"));
                    producto.setDescripcion(resultado.getString("Descripcion"));
                    producto.setPrecio(resultado.getDouble("Precio"));
                    producto.setMarca(resultado.getString("Marca"));
                    producto.setImagen(resultado.getString("Imagen"));

                    productos.add(producto);
                }
            } catch (SQLException e) {
                System.out.println("Error al ejecutar la sentencia");
            }
        } catch (SQLException e) {
            System.out.println("Error al abrir conexion con la BBDD");
        } finally {
            this.closeConnection();
        }

        return productos;
    }

    @Override
    public ArrayList<Producto> getProductosCategoria(int idCategoria) {

        ArrayList<Producto> productos = new ArrayList();

        String consulta = "select * from productos where IdCategoria=?; ";


        try {

            PreparedStatement preparada = ConnectionFactory.abrirConexion().prepareStatement(consulta);
            preparada.setInt(1, idCategoria);

            ResultSet resultado = preparada.executeQuery();

            while (resultado.next()) {

                Producto producto = new Producto();
                producto.setIdProducto(resultado.getInt("IdProducto"));
                producto.setIdCategoria(resultado.getInt("IdCategoria"));
                producto.setNombre(resultado.getString("Nombre"));
                producto.setDescripcion(resultado.getString("Descripcion"));
                producto.setPrecio(resultado.getDouble("Precio"));
                producto.setMarca(resultado.getString("Marca"));
                producto.setImagen(resultado.getString("Imagen"));

                productos.add(producto);
            }

            preparada.close();
            resultado.close();

        } catch (SQLException e) {
            System.out.println("Problema con la base de datos");

        } finally {
            this.closeConnection();

        }

        return productos;
    }

    @Override
    public Producto getProductoMasVendido() {

        Producto masVendido = new Producto();

        String consulta = "SELECT * FROM Productos WHERE idProducto= (select idProducto from lineaspedidos group by idProducto order by sum(cantidad) desc limit 1); ";

        try {
            Statement sentencia = ConnectionFactory.abrirConexion().createStatement();
            try {
                ResultSet resultado = sentencia.executeQuery(consulta);

                while (resultado.next()) {

                    masVendido.setIdProducto(resultado.getInt("IdProducto"));
                    masVendido.setIdCategoria(resultado.getInt("IdCategoria"));
                    masVendido.setNombre(resultado.getString("Nombre"));
                    masVendido.setDescripcion(resultado.getString("Descripcion"));
                    masVendido.setPrecio(resultado.getDouble("Precio"));
                    masVendido.setMarca(resultado.getString("Marca"));
                    masVendido.setImagen(resultado.getString("Imagen"));

                }
            } catch (SQLException e) {
                System.out.println("Error al ejecutar la sentencia");
            }
        } catch (SQLException e) {
            System.out.println("Error al abrir conexion con la BBDD");
        } finally {
            this.closeConnection();
        }
        return masVendido;

    }

    @Override
    public void closeConnection() {
        ConnectionFactory.cerrarConexion();
    }

}
