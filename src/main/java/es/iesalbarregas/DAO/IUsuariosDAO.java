package es.iesalbarregas.DAO;

import es.iesalbarregas.beans.Usuario;

/**
 *
 * @author Ana
 */
public interface IUsuariosDAO {
    
    /**
     *
     * @param email
     * @return Usuario de la base de datos con ese email
     */
    public Usuario getUsuarioEmail(String email);
    
    /**
     *
     * @param email
     * @return true o false dependiendo de si el email ya está en la bbdd
     */
    public boolean comprobarEmail(String email);
    

    /**
     * 
     * @param usuario
     * @return devuelve true si se ha creado correctamente o false si ha habido algún error
     */
    public boolean crearUsuario(Usuario usuario);

    /**
     *
     * @return ultimo idUsuario que ha sido asignado en la BBDD
     */
    public int ultimoIdUsuario();
    
    /**
     * cierra la conexion
     */
    public void closeConnection();  
    
}
