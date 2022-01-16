package es.iesalbarregas.DAOFactory;

import es.iesalbarregas.DAO.ICategoriasDAO;
import es.iesalbarregas.DAO.IPedidosDAO;
import es.iesalbarregas.DAO.IProductosDAO;
import es.iesalbarregas.DAO.IUsuariosDAO;
import es.iesalbarregas.beans.Categoria;
import es.iesalbarregas.beans.Usuario;
import java.util.ArrayList;

/**
 *
 * @author Ana
 */
public abstract class DAOFactory {
    
    public static final int MYSQL = 1;
    
    public abstract IUsuariosDAO getUsuarioEmail(String email, String contrasenia);
    
    public abstract IUsuariosDAO comprobarEmail(String email);
    
    public abstract IUsuariosDAO crearUsuario(Usuario usuario);
    
    public abstract IUsuariosDAO ultimoIdUsuario();
    
    public abstract IUsuariosDAO updateUsuario(Usuario usuario);
    
    public abstract IUsuariosDAO updateAvatar(int idUsuario, String avatar);
    
    public abstract IUsuariosDAO updateUltimoAcceso(int idUsuario);
    
    public abstract IProductosDAO getProductos();
    
    public abstract IProductosDAO getProductosCategoria(Categoria categoria);
    
    public abstract IProductosDAO getProductoMasVendido();
    
    public abstract IProductosDAO getProductoId(int idProducto);
    
    public abstract ICategoriasDAO getCategorias();
    
    public abstract IPedidosDAO getPedidos(int idUsuario);    
    
    public static DAOFactory getDAOFactory(int tipoBBDD) {
        
        DAOFactory DAOFactory = null;

        switch (tipoBBDD) {
            case MYSQL:
                DAOFactory = new MySQLDAOFactory();
                break;

        }

        return DAOFactory;
    }
}
