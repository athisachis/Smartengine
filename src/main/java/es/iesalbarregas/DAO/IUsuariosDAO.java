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
     * @param contrasenia
     * @return Usuario de la base de datos con ese email
     */
    public Usuario getUsuarioEmail(String email, String contrasenia);
    
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
     *
     * @param usuario
     * @return true si se ha hecho correctamente
     */
    public boolean updateUsuario(Usuario usuario);
    
    /**
     *
     * @param idUsuario
     * @param avatar
     */
    public void updateAvatar(int idUsuario, String avatar);
 
    /**
     *
     * @param idUsuario
     */
    public void updateUltimoAcceso(int idUsuario);
    
    /**
     * cierra la conexion
     */
    public void closeConnection();  
    
}
