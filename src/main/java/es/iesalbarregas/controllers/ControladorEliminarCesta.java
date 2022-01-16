package es.iesalbarregas.controllers;

import java.io.IOException;
import java.io.PrintWriter;
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

        //Usuario registrado
        if (request.getSession().getAttribute("usuario") != null) {

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
        request.getRequestDispatcher("/JSP/Cesta.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
