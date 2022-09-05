package Controller;

import Model.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(name = "ServletEffettuaOrdine", value = "/ServletEffettuaOrdine")
public class ServletEffettuaOrdine extends HttpServlet {
    /*
    Questa Servlet ha come scopo il prelievo dei dati inseriti dall'utente,
    relativi alla spedizione del prodotto.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String via = request.getParameter("via");
        Integer numero = Integer.parseInt(request.getParameter("numero"));
        String cap = request.getParameter("cap");
        Integer idUtente = Integer.parseInt(request.getParameter("id_utente"));
        Double importo = Double.parseDouble(request.getParameter("importo"));
        String codice_sconto = request.getParameter("sconto");

        HttpSession session = request.getSession();
        session.setAttribute("importoNonScontato", importo);

        ScontoDAO scontoDAO = new ScontoDAO();
        ScontoUtenteDAO scontoUtenteDAO = new ScontoUtenteDAO();
        Sconto sconto = scontoDAO.doRetrieveScontoById(codice_sconto);
        //Verifica se lo sconto inserito è effettivamente presente nel database.
        if(sconto != null){
            importo = Math.round((importo - (importo*sconto.getPercentualeSconto()/100))*100.0)/100.0;

            //Verifica se lo sconto è stato già utilizzato da quell'utente.
            boolean couponJustUsed = false;
            ArrayList<Integer> listaUtentiSconto = (ArrayList<Integer>) scontoUtenteDAO.doRetrieveUserByCodiceSconto(codice_sconto);
            for (int i = 0; i < listaUtentiSconto.size(); i++) {
                if (listaUtentiSconto.get(i) == idUtente) {
                    couponJustUsed = true;
                }
            }
            if(!couponJustUsed){
                //Se non è stato utilizzato, memorizza il fatto che l'utente stia usufruendo di quello sconto.
                scontoUtenteDAO.doSave(idUtente, codice_sconto);
            }
        }

        OrdineDAO ordineDAO = new OrdineDAO();
        Ordine ordine = new Ordine();
        ordine.setVia(via);
        ordine.setNumero_civico(numero);
        ordine.setCap(cap);
        ordine.setId_utente(idUtente);
        java.util.Date dataUtil = new java.util.Date();
        Date dataSql = new Date(dataUtil.getTime());
        ordine.setData_ordine(dataSql);
        ordine.setImporto(importo);

        //Memorizza l'ordine nel database
        ordineDAO.doSave(ordine);

        Carrello carrello = (Carrello) session.getAttribute("carrello");
        ArrayList<Prodotto> listaProdotti = new ArrayList<>();

        try{
            listaProdotti = (ArrayList<Prodotto>) carrello.getAllProduct();
        } catch(NullPointerException e){
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("/WEB-INF/loginFailed.jsp");
            dispatcher.forward(request,response);
        }

        OrdineProdottoDAO ordineProdottoDAO = new OrdineProdottoDAO();
        //Memorizza i prodotti inclusi nell'ordine.
        for(Prodotto p: listaProdotti){
            ordineProdottoDAO.doSave(ordine.getId_ordine(), p.getId_prodotto(), carrello.getListaProdotti().get(p), carrello.totaleProdotto(p), p.getPrezzo(), p.getImmagine());
        }

        ProdottoDAO prodottoDAO = new ProdottoDAO();
        //Riduce la quantità disponibili dei prodotti che sono stati appena ordinati.
        for(Prodotto p: listaProdotti){
            try {
                prodottoDAO.doUpdateQuantita(p.getId_prodotto(), p.getQuantita() - carrello.getListaProdotti().get(p));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        UtenteDAO utenteDAO = new UtenteDAO();
        Utente utente = utenteDAO.doRetrieveById(idUtente);
        ArrayList<OrdineProdotto> listaOrdineProdotto = (ArrayList<OrdineProdotto>) ordineProdottoDAO.doRetrieveOrdineProdottoByIdOrder(ordine.getId_ordine());

        session.setAttribute("listaProdottiOrdine", listaProdotti);
        session.setAttribute("listaOrdineProdotto", listaOrdineProdotto);
        session.setAttribute("ordineEffettuato", ordine);
        session.setAttribute("clienteOrdine", utente);
        session.removeAttribute("carrello");

        response.sendRedirect("ServletRiepilogoOrdineRedirect");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
