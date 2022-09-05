package Controller;

import Model.Carrello;
import Model.Prodotto;
import Model.ProdottoDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(name = "ServletDeleteProductCart", value = "/ServletDeleteProductCart")
public class ServletDeleteProductCart extends HttpServlet {
    /*
    Questa Servlet si occupa di eliminare un prodotto dal carrello.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idProdotto = request.getParameter("codice");

        Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");

        ProdottoDAO prodottoDAO = new ProdottoDAO();
        Prodotto prodotto = prodottoDAO.doRetrieveById(idProdotto);

        //Verifichiamo se quel prodotto è contenuto nel carrello.
        if(carrello.containsProduct(prodotto))
            carrello.removeProductFromCart(prodotto);

        //Verifichiamo che il prodotto sia stato rimosso dal carrello e comunichiamo la nuova spesa totale del carrello.
        if(!carrello.containsProduct(prodotto)){
            response.getWriter().append("[");

            String obj = "";
            obj += "{\"totaleCarrello\": \"" + carrello.totaleCarrello() + "\"" +
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
