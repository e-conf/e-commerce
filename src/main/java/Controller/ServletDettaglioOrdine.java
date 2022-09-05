package Controller;

import Model.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ServletDettaglioOrdine", value = "/ServletDettaglioOrdine")
public class ServletDettaglioOrdine extends HttpServlet {
    /*
    Questa Servlet si occupa di fornire i dati relativi all'ordine, il cui id
    viene passato come parametro.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id_ordine = Integer.parseInt(request.getParameter("id_ordine"));

        OrdineDAO ordineDAO = new OrdineDAO();
        Ordine ordine = ordineDAO.doRetrieveOrderById(id_ordine);

        response.setContentType("text/plain;charset=UTF-8");
        response.getWriter().append("[");

        String obj = "";
        obj += "{\"id_ordine\": \"" + id_ordine + "\"," +
                "\"data_ordine\": \"" + ordine.getData_ordine() + "\"," +
                "\"importo\": \"" + ordine.getImporto() + "\"" +
                "}";
        response.getWriter().append(obj);
        response.getWriter().append("]");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
