package es.iesalbarregas.controllers;

import es.iesalbarregas.DAO.MySQLPedidosDAO;
import es.iesalbarregas.beans.LineaCesta;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ana
 */
@WebServlet(name = "ControladorEliminarCesta", urlPatterns = {"/ControladorEliminarCesta"})
public class ControladorEliminarCesta extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getSession().getAttribute("cestaSmartengine") != null) {
            //Usuario registrado
            if (request.getSession().getAttribute("usuario") != null) {

                //Para obtener el idPedido
                ArrayList<LineaCesta> cesta = (ArrayList<LineaCesta>) request.getSession().getAttribute("cestaSmartengine");

                LineaCesta cualquiera = cesta.get(0);

                int idPedido = cualquiera.getIdPedido();

                MySQLPedidosDAO pdao = new MySQLPedidosDAO();

                pdao.eliminarPedido(idPedido);

            } else {

                //Si la la cookie existe, la eliminamos
                Cookie cookie = null;

                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (int i = 0; i < cookies.length; i++) {
                        if (cookies[i].getName().equals("cestaSmartengine")) {
                            cookie = cookies[i];
                            break;
                        }
                    }

                    if (cookie != null) {
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }

                }
            }

            request.getSession().removeAttribute("cestaSmartengine");
            //Redirigimos a la tienda
            request.getRequestDispatcher("/JSP/Tienda.jsp").forward(request, response);
        }else{
            request.getRequestDispatcher("/JSP/Cesta.jsp").forward(request, response);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
