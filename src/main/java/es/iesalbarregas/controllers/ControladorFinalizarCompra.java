package es.iesalbarregas.controllers;

import es.iesalbarregas.DAO.MySQLPedidosDAO;
import es.iesalbarregas.beans.LineaCesta;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
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
@WebServlet(name = "ControladorFinalizarCompra", urlPatterns = {"/ControladorFinalizarCompra"})
public class ControladorFinalizarCompra extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getSession().getAttribute("cestaSmartengine") != null) {

            if (request.getSession().getAttribute("usuario") != null) {

                ArrayList<LineaCesta> cesta = new ArrayList();

                cesta = (ArrayList<LineaCesta>) request.getSession().getAttribute("cestaSmartengine");

                double importeTotal = 0;

                for (LineaCesta lineaCesta : cesta) {

                    importeTotal += (lineaCesta.getPrecioUnitario() * lineaCesta.getCantidad());
                }

                double iva = importeTotal * 21 / 100;
                //Redondeamos a dos decimales
                double importeRedondeado = Math.round(importeTotal * 100.0) / 100.0;

                //Redondeamos a dos decimales
                double ivaRedondeado = Math.round(iva * 100.0) / 100.0;

                request.setAttribute("importeTotal", importeRedondeado);
                request.setAttribute("iva", ivaRedondeado);

                request.getSession().removeAttribute("cestaSmartengine");

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

                MySQLPedidosDAO pdao = new MySQLPedidosDAO();

                LineaCesta cualquiera = cesta.get(0);

                int idPedido = cualquiera.getIdPedido();

                pdao.finalizarPedido(idPedido);

                request.getRequestDispatcher("/JSP/Factura.jsp").forward(request, response);

            } else {
                request.getRequestDispatcher("/JSP/Registro.jsp").forward(request, response);
                
            }

        } else {

            request.getRequestDispatcher("/JSP/Cesta.jsp").forward(request, response);

        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
