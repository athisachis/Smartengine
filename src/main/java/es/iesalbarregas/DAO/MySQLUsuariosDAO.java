package es.iesalbarregas.DAO;

import es.iesalbarregas.beans.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ana
 */
public class MySQLUsuariosDAO implements IUsuariosDAO{

    /**
     *
     * @param mail
     * @return devuelve todos los datos del usuario que tiene ese email en la BBDD
     */
    
    
    @Override

    public Usuario getUsuarioEmail(String mail, String contrasenia){
        
        Usuario usuarioBBDD = new Usuario();
        
        String consulta = "select * from usuarios where email=? AND password=md5(?); ";
        
        try {
            
            PreparedStatement preparada = ConnectionFactory.abrirConexion().prepareStatement(consulta);
            preparada.setString(1, mail);
            preparada.setString(2, contrasenia);
            
            ResultSet resultado = preparada.executeQuery();
            
                while (resultado.next()) {
                    
                
                    Usuario usuario = new Usuario();
                    
                    usuario.setIdUsuario(resultado.getInt("idUsuario"));
                    usuario.setEmail(resultado.getString("email"));
                    usuario.setContrasenia(resultado.getString("password"));
                    usuario.setNombre(resultado.getString("nombre"));
                    usuario.setApellidos(resultado.getString("apellidos"));
                    usuario.setNif(resultado.getString("nif"));
                    usuario.setTelefono(resultado.getString("telefono"));
                    usuario.setDireccion(resultado.getString("direccion"));
                    usuario.setCodPostal(resultado.getString("codigoPostal"));
                    usuario.setLocalidad(resultado.getString("localidad"));
                    usuario.setProvincia(resultado.getString("provincia"));
                    usuario.setAvatar(resultado.getString("avatar"));

                    usuarioBBDD= usuario;
                }
                
                preparada.close();
                resultado.close();
            
        } catch (SQLException e) {
            System.out.println("Problema con la base de datos");
            
        }finally{
            this.closeConnection();
            
        }
        
        return usuarioBBDD;
                
    }
    
    
    @Override

    public boolean comprobarEmail(String mail){        
        
        String consulta = "select email from usuarios where email=?; ";
        boolean check=false;
        
        try {
            
            PreparedStatement preparada = ConnectionFactory.abrirConexion().prepareStatement(consulta);
            preparada.setString(1, mail);
            
            ResultSet resultado = preparada.executeQuery();
            
            if (resultado.next()) {
                check=true;
            }
                
            preparada.close();
            resultado.close();
                
            
            
        } catch (SQLException e) {
            System.out.println("Problema con la base de datos");
            
        }finally{
            this.closeConnection();
            
        }
        
        return check;
                
    }
    
    @Override
    public boolean crearUsuario(Usuario usuario) {
        
        boolean check=false;        
        Connection conex = ConnectionFactory.abrirConexion();
     
        String consulta = "insert into usuarios(email, password, nombre, apellidos, nif, telefono, direccion, codigoPostal, localidad, provincia, ultimoAcceso) values(?, md5(?), ?, ?, ?, ?, ?, ?, ?, ?, now())";
        
        try {
            
            conex.setAutoCommit(false);
            
            PreparedStatement preparada = conex.prepareStatement(consulta);
            
            preparada.setString(1, usuario.getEmail());
            preparada.setString(2, usuario.getContrasenia());
            preparada.setString(3, usuario.getNombre());
            preparada.setString(4, usuario.getApellidos());
            preparada.setString(5, usuario.getNif());
            preparada.setString(6, usuario.getTelefono());
            preparada.setString(7, usuario.getDireccion());
            preparada.setString(8, usuario.getCodPostal());
            preparada.setString(9, usuario.getLocalidad());
            preparada.setString(10, usuario.getProvincia());
            
            
            preparada.execute();            
                
            preparada.close();
            
            conex.commit();
            check=true;
            
        } catch (SQLException e) {
            System.out.println("Problema con la base de datos");
            try {
                conex.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLUsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            this.closeConnection();
            
        }                
        
        
        return check;
    }
    
    @Override
    public int ultimoIdUsuario(){
        
        int ultimoId=0;
        
        String consulta = "SELECT idUsuario FROM usuarios order by idUsuario DESC LIMIT 1; ";
        
        try {
            Statement sentencia = ConnectionFactory.abrirConexion().createStatement();
            
            ResultSet resultado = sentencia.executeQuery(consulta);
            
            while (resultado.next()) {
                ultimoId= resultado.getInt("IdUsuario");
            }
        } catch (SQLException ex) {
            System.out.println("Problema con la base de datos");        
        }finally{
            this.closeConnection();
        }
        
        return ultimoId;
        
       
        
        
    }
    
    /**
     *
     * @param usuario
     * @return check true si se ha modificado el usuario correctamente, de lo contrario dar?? false
     */
    @Override
    public boolean updateUsuario(Usuario usuario){
        
        boolean check = false;
        
        String consulta = "update usuarios SET email=?, password=md5(?), nombre=?, apellidos=?, nif=?, telefono=?, direccion=?, codigoPostal=?, localidad=?, provincia=?, avatar=? WHERE idUsuario=?;";

        Connection conex = ConnectionFactory.abrirConexion();
        
        try {
            conex.setAutoCommit(false);
            PreparedStatement preparada = conex.prepareStatement(consulta);
            
            preparada.setString(1, usuario.getEmail());
            preparada.setString(2, usuario.getContrasenia());
            preparada.setString(3, usuario.getNombre());
            preparada.setString(4, usuario.getApellidos());
            preparada.setString(5, usuario.getNif());
            preparada.setString(6, usuario.getTelefono());
            preparada.setString(7, usuario.getDireccion());
            preparada.setString(8, usuario.getCodPostal());
            preparada.setString(9, usuario.getLocalidad());
            preparada.setString(10, usuario.getProvincia());
            
            
            String avatar;
            if (usuario.getAvatar()==null) {
                avatar="default.png";
            }else{
                avatar=usuario.getAvatar();
            }
            
            preparada.setInt(12, usuario.getIdUsuario());
            
            preparada.setString(11, avatar);
            
            preparada.executeUpdate();            
                
            preparada.close();
            
            conex.commit();
            check=true;
            
        } catch (SQLException e) {
            System.out.println("Problema con la base de datos");
            try {
                conex.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLUsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            this.closeConnection();
            
        }                
        
        return check;
        
    }
    
    /**
     *
     * @param idUsuario
     * @param avatar
     */
    @Override
   public void updateAvatar(int idUsuario, String avatar){        
        
        String consulta = "update usuarios SET avatar=? WHERE idUsuario=?;";
        Connection conex = ConnectionFactory.abrirConexion();
        
        try {
            
            conex.setAutoCommit(false);
            PreparedStatement preparada = conex.prepareStatement(consulta);
            
            preparada.setString(1, avatar);
            preparada.setInt(2, idUsuario);
            
            
            preparada.executeUpdate();            
                
            preparada.close();
            
            conex.commit();
            
        } catch (SQLException e) {
            System.out.println("Problema con la base de datos");
            try {
                conex.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLUsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            this.closeConnection();
            
        }                
        
        
    }
   
    @Override
   public void updateUltimoAcceso(int idUsuario){
       
        
        String consulta = "update usuarios SET ultimoAcceso=now() WHERE idUsuario=?;";
        Connection conex = ConnectionFactory.abrirConexion();
        
        try {
            
            conex.setAutoCommit(false);
            PreparedStatement preparada = conex.prepareStatement(consulta);
            
            preparada.setInt(1, idUsuario);
                        
            preparada.executeUpdate();            
                
            preparada.close();
            
            conex.commit();
            
        } catch (SQLException e) {
            System.out.println("Problema con la base de datos");
            try {
                conex.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLUsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            this.closeConnection();
            
        }  
       
   }
    
    @Override
    public void closeConnection() {
        ConnectionFactory.cerrarConexion();
    }

    

    
}
