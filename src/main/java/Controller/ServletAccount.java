package Controller;

import Model.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "ServletAccount", value = "/ServletAccount")
public class ServletAccount extends HttpServlet {
    /*
    Questa Servlet si occupa di gestire il direzionamento del flusso verso
    l'opportuna JSP in base al tipo di account: amministratore e utente.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Utente utente = (Utente) session.getAttribute("utente");

        OrdineDAO ordineDAO = new OrdineDAO();

        //Verifichiamo se l'utente è amministratore o meno.
        if(!utente.isAdmin()) {
            //Memorizziamo in sessione alcune informazioni relative all'utente e ai suoi ordini.
            ArrayList<Ordine> listaOrdini = (ArrayList<Ordine>) ordineDAO.doRetrieveByUser(utente.getId());
            double spesaTotaleUtente = ordineDAO.doRetrieveOrderAmountByUser(utente.getId());

            spesaTotaleUtente = Math.round(spesaTotaleUtente * 100.0) / 100.0;
            if (listaOrdini != null) {
                session.setAttribute("listaOrdini", listaOrdini);
                session.setAttribute("spesaTotaleUtente", spesaTotaleUtente);
            }
        } else {
            //Memorizziamo in sessione alcune informazioni relative alla web-application e agli utenti di essa.
            UtenteDAO utenteDAO = new UtenteDAO();
            OrdineProdottoDAO ordineProdottoDAO = new OrdineProdottoDAO();

            int numeroOrdini = ordineDAO.doRetrieveAllOrderNumber();
            if(numeroOrdini > 0) {
                HashMap<String, Integer> prodottoBestSelling = ordineProdottoDAO.doRetrieveBestSellingProduct();
                HashMap<String, Double> prodottoMostRemunerative = ordineProdottoDAO.doRetrieveMostRemunerativeProduct();

                session.setAttribute("prodottoPiuVenduto", prodottoBestSelling);
                session.setAttribute("prodottoPiuRemunerativo", prodottoMostRemunerative);
            }

            int numeroUtentiRegistrati = utenteDAO.doRetrieveUserRegisteredNumber();
            double spesaTotale = ordineDAO.doRetrieveAllOrderAmount();
            session.setAttribute("numeroUtentiRegistrati", numeroUtentiRegistrati);
            session.setAttribute("numeroOrdini", numeroOrdini);
            session.setAttribute("spesaTotale", spesaTotale);
        }

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/account.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
