package es.iesalbarregas.controllers;

import es.iesalbarregas.DAO.MySQLUsuariosDAO;
import es.iesalbarregas.DAOFactory.DAOFactory;
import es.iesalbarregas.beans.Usuario;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Ana
 */
@WebServlet(name = "ControladorRegistro", urlPatterns = {"/ControladorRegistro"})
public class ControladorRegistro extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuario = new Usuario();
        String tipoArchivo = null;
        String nombreArchivo = "default.png";

        boolean check = false;

        DAOFactory DAOFactory = null;

        DAOFactory = DAOFactory.getDAOFactory(1);

        MySQLUsuariosDAO udao = new MySQLUsuariosDAO();

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        HashMap<String, String> map = new HashMap();

        Iterator<FileItem> campos;

        List<FileItem> parametros = null;

        try {
            parametros = upload.parseRequest(request);

            campos = parametros.iterator();

            while (campos.hasNext()) {

                FileItem esteCampo = campos.next();

                if (!esteCampo.getFieldName().equals("boton") && !esteCampo.getFieldName().equals("avatar")) {

                    map.put(esteCampo.getFieldName(), esteCampo.getString("UTF-8"));
                }

            }

            try {
                BeanUtils.populate(usuario, map);

                //Creo finalmente el usuario
                check = udao.crearUsuario(usuario);

                Usuario usuarioCreado = udao.getUsuarioEmail(usuario.getEmail(), usuario.getContrasenia());

                //Volvemos a recorrer para buscar el avatar
                Iterator<FileItem> it = parametros.iterator();
                while (it.hasNext()) {

                    FileItem elemento = it.next();

                    if (!elemento.isFormField()) {

                        if (elemento.getFieldName().equals("avatar")) {

                            if (!elemento.getString().equals("")) {
                                if (elemento.getContentType().equals("image/png") || elemento.getContentType().equals("image/jpeg")) {

                                    int idAsignar = usuarioCreado.getIdUsuario();

                                    nombreArchivo = "avatarN" + idAsignar;
                                    String rutaAbsoluta = request.getServletContext().getRealPath("/");

                                    StringBuilder ruta = new StringBuilder();
                                    ruta.append(rutaAbsoluta).append("..").append(File.separator).append("..").append(File.separator).append("src").append(File.separator).append("main").append(File.separator).append("webapp").append(File.separator).append("IMG").append(File.separator).append("avatares");

                                    //tamaño máximo de imagen que se puede subir
                                    upload.setSizeMax(1024 * 512);

                                    if (elemento.getContentType().equals("image/png")) {

                                        tipoArchivo = ".png";

                                    } else {

                                        tipoArchivo = ".jpeg";

                                    }

                                    nombreArchivo += tipoArchivo;

                                    //Se crea el archivo
                                    File archivo = new File(ruta + "/" + nombreArchivo);

                                    try {
                                        //Escribo el fichero en el servidor (lo guardo)
                                        elemento.write(archivo);
                                    } catch (Exception ex) {
                                        System.out.println(ex.getMessage());
                                    }

                                    usuarioCreado.setAvatar(nombreArchivo);

                                    udao.updateAvatar(usuarioCreado.getIdUsuario(), usuarioCreado.getAvatar());

                                }
                            } 

                        }

                    }
                }

                if (check) {

                    request.getSession().setAttribute("usuario", usuarioCreado);
                    request.getRequestDispatcher("JSP/Tienda.jsp").forward(request, response);
                } else {
                    String error = "error";
                    request.setAttribute(error, error);
                }

            } catch (IllegalAccessException ex) {
                Logger.getLogger(ControladorRegistro.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(ControladorRegistro.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileUploadException ex) {
            Logger.getLogger(ControladorRegistro.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
