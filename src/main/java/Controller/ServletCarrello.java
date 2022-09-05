package Controller;

import Model.Carrello;
import Model.Prodotto;
import Model.ProdottoDAO;
import com.mysql.cj.Session;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(name = "ServletCarrello", value = "/ServletCarrello")
public class ServletCarrello extends HttpServlet {
    /*
    Questa Servlet ha il compito di gestire il carrello.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idProdotto = request.getParameter("id_prodotto");
        Integer quantita = Integer.parseInt(request.getParameter("quantita"));

        ProdottoDAO prodottoDAO = new ProdottoDAO();
        Prodotto prodotto = (Prodotto) prodottoDAO.doRetrieveById(idProdotto);

        HttpSession session = request.getSession();
        Carrello carrello = new Carrello();

            //Verifichiamo se è già memorizzato un carrello in sessione. Se no, lo creiamo.
            if (session.getAttribute("carrello") == null) {
                HashMap<Prodotto, Integer> hashMap = new HashMap<>();
                hashMap.put(prodotto, quantita);
                carrello.setListaProdotti(hashMap);
            } else {
                carrello = (Carrello) session.getAttribute("carrello");
                //Verifichiamo che la quantità del prodotto nel carrello, non ecceda quella disponibile.
                if(prodotto.getQuantita() >= quantita+carrello.getQuantity(prodotto)) {
                    //Verifichiamo se il prodotto è già presente nel carrello. Se sì, ne aggiorniamo la quantità,
                    if (carrello.containsProduct(prodotto)) {
                        int oldValue = carrello.getQuantity(prodotto);
                        carrello.changeQuantityProduct(prodotto, oldValue + 1);
                    } else {
                        carrello.getListaProdotti().put(prodotto, quantita);
                    }
                }
            }

        session.setAttribute("carrello", carrello);

        response.getWriter().append("[");

        String obj = "";
        obj += "{\"numeroElementi\": \"" + carrello.getListaProdotti().size() + "\"" +
                "}";

        response.getWriter().append(obj);
        response.getWriter().append("]");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
