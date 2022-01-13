/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iesalbarregas.controllers;

import es.iesalbarregas.DAO.MySQLUsuariosDAO;
import es.iesalbarregas.DAOFactory.DAOFactory;
import es.iesalbarregas.beans.Usuario;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author error
 */
@WebServlet(name = "ControladorPerfil", urlPatterns = {"/ControladorPerfil"})
public class ControladorPerfil extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        if (request.getSession().getAttribute("usuario")!=null) {
            request.getRequestDispatcher("JSP/EditarPerfil.jsp").forward(request, response);
        }else{
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
        
        
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Usuario usuario = new Usuario();
        String tipoArchivo = null;
        String nombreArchivo = "default.png";
        //Usuario que esta actualmente logueado
        Usuario usuarioSesion = (Usuario) request.getSession().getAttribute("usuario");

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

                if (!esteCampo.getFieldName().equals("boton")) {

                    map.put(esteCampo.getFieldName(), esteCampo.getString("UTF-8"));
                }

            }

        } catch (FileUploadException ex) {
            Logger.getLogger(ControladorRegistro.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!map.get("avatar").equals("")) {

            int idAsignar = usuarioSesion.getIdUsuario();

            nombreArchivo = "avatarN" + idAsignar;
            String rutaAbsoluta = request.getServletContext().getRealPath("/");

            StringBuilder ruta = new StringBuilder();
            ruta.append(rutaAbsoluta).append("..").append(File.separator).append("..").append(File.separator).append("src").append(File.separator).append("main").append(File.separator).append("webapp").append(File.separator).append("IMG").append(File.separator).append("avatares");

            //tamaño máximo de imagen que se puede subir
            upload.setSizeMax(1024 * 512);
            

            Iterator<FileItem> it = parametros.iterator();
            while (it.hasNext()) {
                
                FileItem elemento = it.next();
                
                if (!elemento.isFormField()) {
                    
                    if (elemento.getContentType().equals("image/png") || elemento.getContentType().equals("image/jpeg")) {
                        
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
                        
                    }
                    
                }
            }
            
        }

        try {
            BeanUtils.populate(usuario, map);
            
            Date ultimoAcceso = Calendar.getInstance().getTime();
            usuario.setUltimoAcceso(ultimoAcceso);
            if (!map.get("avatar").equals("")) {
                usuario.setAvatar(nombreArchivo);
            }else{
                usuario.setAvatar(nombreArchivo);
            }
            
            //Le pongo el id del usuario que está guardado en sesión para poder modificar en la bbdd el usuario con ese id            
            int idUsuario = usuarioSesion.getIdUsuario();
            
            usuario.setIdUsuario(idUsuario);
            
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ControladorRegistro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(ControladorRegistro.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Creo finalmente el usuario
        boolean check;
        
        
        check = udao.updateUsuario(usuario);

        if (check) {

            request.getSession().setAttribute("usuario", usuario);
            request.getRequestDispatcher("JSP/Tienda.jsp").forward(request, response);
        } else {
            String error = "error";
            request.setAttribute(error, error);
        }
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
