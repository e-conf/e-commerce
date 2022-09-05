package Controller;

import Model.Prodotto;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ServletInsertRetrieveData", value = "/ServletInsertRetrieveData")
public class ServletInsertRetrieveData extends HttpServlet {
    /*
    Questa Servlet si occupa di prelevare i dati immessi dall'utente per
    l'inserimento di un nuovo prodotto.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codiceProdotto = (String) request.getParameter("cod");
        String nome = (String) request.getParameter("nome");
        String provenienza = (String) request.getParameter("provenienza");
        String descrizione = (String) request.getParameter("descrizione");
        Integer quantita = Integer.parseInt(request.getParameter("quantita"));
        Double prezzo = Double.parseDouble(request.getParameter("prezzo"));
        String categoria = (String) request.getParameter("categoria");
        String formato = (String) request.getParameter("formato");

        Prodotto prodotto = new Prodotto();
        prodotto.setId_prodotto(codiceProdotto);
        prodotto.setNome(nome);
        prodotto.setProvenienza(provenienza);
        prodotto.setDescrizione(descrizione);
        prodotto.setQuantita(quantita);
        prodotto.setPrezzo(prezzo);
        prodotto.setCategoria(categoria);
        prodotto.setFormato(formato);

        request.getSession().setAttribute("prodottoDaInserire", prodotto);
        RequestDispatcher dispatcher =
            request.getRequestDispatcher("/WEB-INF/insertImage.jsp");
        dispatcher.forward(request, response);
    }
}

