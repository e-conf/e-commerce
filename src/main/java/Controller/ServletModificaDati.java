package Controller;

import Model.Prodotto;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ServletModificaDati", value = "/ServletModificaDati")
public class ServletModificaDati extends HttpServlet {
    /*
    Questa Servlet si occupa di prelevare i dati del prodotto da modificare.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id_prodotto");
        String nome = request.getParameter("nomeProdotto");
        String provenienza = request.getParameter("provenienzaProdotto");
        String descrizione = request.getParameter("descrizioneProdotto");
        Integer quantita = Integer.parseInt(request.getParameter("quantitaProdotto"));
        Double prezzo = Double.parseDouble(request.getParameter("prezzoProdotto"));
        String formato = request.getParameter("formatoVenditaProdotto");
        String categoria = request.getParameter("categoriaProdotto");
        String immagine = request.getParameter("immagineProdotto");

        Prodotto prodotto = new Prodotto();

        prodotto.setId_prodotto(id);
        prodotto.setNome(nome);
        prodotto.setProvenienza(provenienza);
        prodotto.setDescrizione(descrizione);
        prodotto.setQuantita(quantita);
        prodotto.setPrezzo(prezzo);
        prodotto.setFormato(formato);
        prodotto.setCategoria(categoria);
        prodotto.setImmagine(immagine);

        HttpSession session = request.getSession();
        session.setAttribute("prodottoDaModificare", prodotto);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/modificaProdotto.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
