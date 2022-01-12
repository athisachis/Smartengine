package es.iesalbarregas.DAOFactory;

import es.iesalbarregas.DAO.ICategoriasDAO;
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
    
    public abstract IUsuariosDAO getUsuarioEmail(String email);
    
    public abstract IUsuariosDAO comprobarEmail(String email);
    
    public abstract IUsuariosDAO crearUsuario(Usuario usuario);
    
    public abstract IUsuariosDAO ultimoIdUsuario();
    
    public abstract IUsuariosDAO updateUsuario(Usuario usuario);
    
    public abstract IProductosDAO getProductos();
    
    public abstract IProductosDAO getProductosCategoria(Categoria categoria);
    
    public abstract IProductosDAO getProductoMasVendido();
    
    public abstract ICategoriasDAO getCategorias();
    
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
