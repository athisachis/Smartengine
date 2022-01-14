package es.iesalbarregas.DAOFactory;

import es.iesalbarregas.DAO.ICategoriasDAO;
import es.iesalbarregas.DAO.IProductosDAO;
import es.iesalbarregas.DAO.IUsuariosDAO;
import es.iesalbarregas.DAO.MySQLCategoriasDAO;
import es.iesalbarregas.DAO.MySQLProductosDAO;
import es.iesalbarregas.DAO.MySQLUsuariosDAO;
import es.iesalbarregas.beans.Categoria;
import es.iesalbarregas.beans.Usuario;

/**
 *
 * @author Ana
 */
public class MySQLDAOFactory extends DAOFactory{
    
    /**
     *
     * @param email
     * @return usuario de la bbdd con ese email
     */
    @Override
    public IUsuariosDAO getUsuarioEmail(String email, String contrasenia) {
        return new MySQLUsuariosDAO();
    }

    /**
     *
     * @param email
     * @return true si el email ya está en la bbdd, false si no
     */
    @Override
    public IUsuariosDAO comprobarEmail(String email) {
        return new MySQLUsuariosDAO();
    }
    
    /**
     *
     * @param usuario
     * @return true si se ha creado correctamente el usuario
     */
    @Override
    public IUsuariosDAO crearUsuario(Usuario usuario) {
        return new MySQLUsuariosDAO();
    }

    /**
     *
     * @return el último idUsuario que ha sido asignado en la BBDD
     */
    @Override
    public IUsuariosDAO ultimoIdUsuario() {
        return new MySQLUsuariosDAO();
    }
    
    /**
     *
     * @return true si se ha actualizado correctamente el usuario
     */
    @Override
    public IUsuariosDAO updateUsuario(Usuario usuario) {
        return new MySQLUsuariosDAO();
    }
    
    @Override
    public IUsuariosDAO updateAvatar(int idUsuario, String avatar) {
        return new MySQLUsuariosDAO();
    }
    
    @Override
    public IUsuariosDAO updateUltimoAcceso(int idUsuario) {
        return new MySQLUsuariosDAO();
    }
    
    @Override
    public IProductosDAO getProductos() {
        return new MySQLProductosDAO();    
    }

    @Override
    public IProductosDAO getProductosCategoria(Categoria categoria) {
        return new MySQLProductosDAO();
    }
    
    /**
     *
     * @return idProducto del producto más vendido
     */
    @Override
    public IProductosDAO getProductoMasVendido() {
        return new MySQLProductosDAO();
    }
   
    @Override
    public ICategoriasDAO getCategorias() {
        return new MySQLCategoriasDAO();
    }
    
}
