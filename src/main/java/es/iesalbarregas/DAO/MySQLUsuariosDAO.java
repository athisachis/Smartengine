package es.iesalbarregas.DAO;

import es.iesalbarregas.beans.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
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

    public Usuario getUsuarioEmail(String mail){
        
        Usuario usuarioBBDD = new Usuario();
        
        String consulta = "select * from usuarios where email=?; ";
        
        try {
            
            PreparedStatement preparada = ConnectionFactory.abrirConexion().prepareStatement(consulta);
            preparada.setString(1, mail);
            
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
        
     
        String consulta = "insert into usuarios(email, password, nombre, apellidos, nif, telefono, direccion, codigoPostal, localidad, provincia, avatar) values (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";
        
        try {
            
            PreparedStatement preparada = ConnectionFactory.abrirConexion().prepareStatement(consulta);
            
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
                avatar="default.jpg";
            }else{
                avatar=usuario.getAvatar();
            }
            
            preparada.setString(11, avatar);
            
            preparada.execute();            
                
            preparada.close();
            

            check=true;
            
        } catch (SQLException e) {
            System.out.println("Problema con la base de datos");
            
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
     * @return check true si se ha modificado el usuario correctamente, de lo contrario dará false
     */
    @Override
    public boolean updateUsuario(Usuario usuario){
        
        boolean check = false;
        
        String consulta = "update usuarios SET email=?, password=?, nombre=?, apellidos=?, nif=?, telefono=?, direccion=?, codigoPostal=?, localidad=?, provincia=?, avatar=? WHERE idUsuario=?;";

        
        try {
            
            PreparedStatement preparada = ConnectionFactory.abrirConexion().prepareStatement(consulta);
            
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
            

            check=true;
            
        } catch (SQLException e) {
            System.out.println("Problema con la base de datos");
            
        }finally{
            this.closeConnection();
            
        }                
        
        return check;
        
    }
    
    public void closeConnection() {
        ConnectionFactory.cerrarConexion();
    }

    

    
}
