package es.iesalbarregas.models;

import es.iesalbarregas.DAO.MySQLUsuariosDAO;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Ana
 */
public class TratamientoImagenes {
    
    public static void subirAvatar(HttpServletRequest request){
        
        MySQLUsuariosDAO baseDatos = new MySQLUsuariosDAO();
        DiskFileItemFactory factory = new DiskFileItemFactory();
        
        int idAsignar = baseDatos.ultimoIdUsuario()+1;
        
        String fileName = "avatarN"+ idAsignar;
        String ruta = request.getServletContext().getRealPath("IMG/avatares");
        
        
        ServletFileUpload upload = new ServletFileUpload(factory);
        
        //tamaño máximo de imagen que se puede subir
        upload.setSizeMax(1024 * 512);
        
        
    }
    
    
    public static void cargarAvatar() {
        
    }
    
}
