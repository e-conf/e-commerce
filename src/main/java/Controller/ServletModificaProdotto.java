package Controller;

import Model.Prodotto;
import Model.ProdottoDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletModificaProdotto", value = "/ServletModificaProdotto")
public class ServletModificaProdotto extends HttpServlet {
    /*
    Questa Servlet ha come scopo l'aggiornamento dei dati di un prodotto.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        try {
            String id = request.getParameter("cod");
            String nome = request.getParameter("nome");
            String provenienza = request.getParameter("provenienza");
            String descrizione = request.getParameter("descrizione");
            Integer quantita = Integer.parseInt(request.getParameter("quantita"));
            Double prezzo = Double.parseDouble(request.getParameter("prezzo"));
            String formato = request.getParameter("formato");
            String categoria = request.getParameter("categoria");
            String immagine = request.getParameter("image");

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

            ProdottoDAO prodottoDAO = new ProdottoDAO();
            prodottoDAO.doUpdateNome(id, nome);
            prodottoDAO.doUpdateProvenienza(id, provenienza);
            prodottoDAO.doUpdateDescrizione(id, descrizione);
            prodottoDAO.doUpdateQuantita(id, quantita);
            prodottoDAO.doUpdatePrezzo(id, prezzo);
            prodottoDAO.doUpdateFormato(id, formato);
            prodottoDAO.doUpdateCategoria(id, categoria);

            session.setAttribute("modificaProdotto", "ok");
            session.setAttribute("prodottoModificato", prodotto);

        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("modificaProdotto", "not-ok");
        }

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/modificaSuccesso.jsp");
        dispatcher.forward(request,response);

    }

}
