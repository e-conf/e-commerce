package Controller;

import Model.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

@WebServlet(name = "ServletDettaglioProdottiOrdine", value = "/ServletDettaglioProdottiOrdine")
public class ServletDettaglioProdottiOrdine extends HttpServlet {
    /*
    Questa Servlet ci consente di conoscere i dati relativi ai prodotti presenti
    in un'ordine, come la quantità acquistata e così via.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id_ordine = Integer.parseInt(request.getParameter("id_ordine"));

        OrdineDAO ordineDAO = new OrdineDAO();
        Ordine ordine = ordineDAO.doRetrieveOrderById(id_ordine);

        OrdineProdottoDAO ordineProdottoDAO = new OrdineProdottoDAO();
        ArrayList<OrdineProdotto> listaOrdineProdotto = ordineProdottoDAO.doRetrieveOrdineProdottoByIdOrder(ordine.getId_ordine());

        int cont = listaOrdineProdotto.size();

        String obj = "";
        if(listaOrdineProdotto != null) {
            response.setContentType("text/plain;charset=UTF-8");
            response.getWriter().append("[");

            for (int i = 0; i < cont - 1; i++) {
                obj += "{\"id_ordine\": \"" + listaOrdineProdotto.get(i).getId_ordine() + "\"," +
                        "\"id_prodotto\": \"" + listaOrdineProdotto.get(i).getId_prodotto() + "\"," +
                        "\"prezzo\": \"" + listaOrdineProdotto.get(i).getPrezzo_prodotto() + "\"," +
                        "\"quantitaOrdine\": \"" + listaOrdineProdotto.get(i).getQuantita_prodotto() + "\"," +
                        "\"totale\": \"" + listaOrdineProdotto.get(i).getTotale_prodotto() + "\"," +
                        "\"immagine\": \"" + listaOrdineProdotto.get(i).getImmagine_prodotto() + "\"" +
                        "},";
            }

            obj += "{\"id_ordine\": \"" + listaOrdineProdotto.get(cont - 1).getId_ordine() + "\"," +
                    "\"id_prodotto\": \"" + listaOrdineProdotto.get(cont - 1).getId_prodotto() + "\"," +
                    "\"prezzo\": \"" + listaOrdineProdotto.get(cont - 1).getPrezzo_prodotto() + "\"," +
                    "\"quantitaOrdine\": \"" + listaOrdineProdotto.get(cont - 1).getQuantita_prodotto() + "\"," +
                    "\"totale\": \"" + listaOrdineProdotto.get(cont - 1).getTotale_prodotto() + "\"," +
                    "\"immagine\": \"" + listaOrdineProdotto.get(cont - 1).getImmagine_prodotto() + "\"" +
                    "}";

            response.getWriter().append(obj);
            response.getWriter().append("]");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
