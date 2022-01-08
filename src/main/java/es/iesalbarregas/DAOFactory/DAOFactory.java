package es.iesalbarregas.DAOFactory;

import es.iesalbarregas.DAO.IProductosDAO;
import es.iesalbarregas.DAO.IUsuariosDAO;
import es.iesalbarregas.beans.Categoria;
import es.iesalbarregas.beans.Usuario;

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
    
    public abstract IProductosDAO getProductos();
    
     public abstract IProductosDAO getProductosCategoria(Categoria categoria);
    
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
