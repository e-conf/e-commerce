package Controller;

import Model.Carrello;
import Model.Prodotto;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ServletQuantitaCarrello", value = "/ServletQuantitaCarrello")
public class ServletQuantitaCarrello extends HttpServlet {
    /*
    Questa Servlet ha come scopo l'aggiornamento della quantità di un prodotto
    all'interno del carrello.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idProdotto = request.getParameter("prodotto");
        Integer quantita = Integer.parseInt(request.getParameter("quantita"));

        Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");

        //Va ad a modificare la vecchia quantità con quella nuova, passata come parametro nella richiesta.
        for(Prodotto p: carrello.getListaProdotti().keySet()){
            if(p.getId_prodotto().equalsIgnoreCase(idProdotto)){
                if(p.getQuantita() >= quantita) {
                    int oldValue = carrello.getListaProdotti().get(p);
                    carrello.getListaProdotti().replace(p, oldValue, quantita);
                }
            }
        }

        request.getSession().setAttribute("carrello", carrello);
        response.getWriter().append("[");

        double totaleCarrello = Math.round(carrello.totaleCarrello()*100.0)/100.0;
        String obj = "";
        obj += "{\"totaleCarrello\": \"" + totaleCarrello + "\"" +
                "}";

        response.getWriter().append(obj);
        response.getWriter().append("]");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
