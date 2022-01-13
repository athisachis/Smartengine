package es.iesalbarregas.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Ana
 */
public class ConnectionFactory {
    
    private static DataSource dataSource;
    private static Connection conexion=null;
    
    public ConnectionFactory() {
    }
    
    public static Connection abrirConexion(){
        try {
            Context initialContext = new InitialContext();
            dataSource =
            (DataSource)initialContext.lookup
            ("java:comp/env/jdbc/smartengine");
            conexion=dataSource.getConnection();
        } catch(NamingException e) {
            System.out.println("Error al acceder al recurso especificado");
        } catch (SQLException e) {
            System.out.println("Error al abrir la conexion");
        }
        return conexion;
    }
    
    public static void cerrarConexion(){
        if (conexion!=null) {
            try {
                conexion.close();
            } catch (SQLException ex) {
                System.out.println("Error al cerrar la conexion");
            }
        }
    }
    
}
