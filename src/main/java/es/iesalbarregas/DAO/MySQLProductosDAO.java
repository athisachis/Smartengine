package es.iesalbarregas.DAO;

import es.iesalbarregas.beans.Categoria;
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
    public ArrayList<Producto> getProductosCategoria(Categoria categoria) {

        ArrayList<Producto> productos = new ArrayList();
        
        String consulta = "select * from productos where IdCategoria=?; ";
        
        int idCategoria = categoria.getIdCategoria();
        
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
            
        }finally{
            this.closeConnection();
            
        }
        
        return productos;
    }
    
    @Override
    public void closeConnection() {
    ConnectionFactory.cerrarConexion();
}

}
