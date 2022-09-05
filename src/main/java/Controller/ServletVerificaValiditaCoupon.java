package Controller;

import Model.Sconto;
import Model.ScontoDAO;
import Model.ScontoUtenteDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "ServletVerificaValiditaCoupon", value = "/ServletVerificaValiditaCoupon")
public class ServletVerificaValiditaCoupon extends HttpServlet {
    /*
    Questa Servlet ha come scopo quello di verificare che il coupon
    inserito dall'utente sia valido, quindi si va a verificare se il
    codice esiste, se è scaduto e se è stato già utilizzato dallo stesso utente.
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codice_sconto = request.getParameter("codice_sconto");
        Integer id_utente = Integer.parseInt(request.getParameter("id_utente"));

        ScontoDAO scontoDAO = new ScontoDAO();
        Sconto sconto = scontoDAO.doRetrieveScontoById(codice_sconto);

        //Verifichiamo se è stato inserito o meno qualcosa nel campo del form relativo al coupon.
        if(codice_sconto.equalsIgnoreCase("")||codice_sconto.equalsIgnoreCase(" ")){
            response.getWriter().append("[");

            String obj = "";
            obj += "{\"validita\": \"" + "null" + "\"" + "}";

            response.getWriter().append(obj);
            response.getWriter().append("]");
        } else {
            //Verifichiamo se il coupon esiste o meno.
            if (sconto == null) {
                response.getWriter().append("[");

                String obj = "";
                obj += "{\"validita\": \"" + "false" + "\"" + "}";

                response.getWriter().append(obj);
                response.getWriter().append("]");
            } else {
                boolean couponJustUsed = false;
                ScontoUtenteDAO scontoUtenteDAO = new ScontoUtenteDAO();
                ArrayList<Integer> listaUtentiSconto = (ArrayList<Integer>) scontoUtenteDAO.doRetrieveUserByCodiceSconto(codice_sconto);

                for (int i = 0; i < listaUtentiSconto.size(); i++) {
                    if (listaUtentiSconto.get(i) == id_utente) {
                        couponJustUsed = true;
                    }
                }

                //Verifichiamo se il coupon è stato già utilizzato in precedenza dallo stesso utente.
                if (!couponJustUsed) {
                    Date dataOrdine = new Date();
                    //Verifichiamo se il coupon è scaduto o meno.
                    if(dataOrdine.getTime() > sconto.getDataFineSconto().getTime()){
                        response.getWriter().append("[");

                        String obj = "";
                        obj += "{\"validita\": \"" + "scaduto" + "\"," +
                                "\"data\": \"" + sconto.getDataFineSconto() + "\"" +
                                "}";

                        response.getWriter().append(obj);
                        response.getWriter().append("]");
                    }else {
                        response.getWriter().append("[");

                        String obj = "";
                        obj += "{\"validita\": \"" + "true" + "\"," +
                                "\"percentuale\": \"" + sconto.getPercentualeSconto() + "\"" +
                                "}";

                        response.getWriter().append(obj);
                        response.getWriter().append("]");
                    }
                } else {

                    response.getWriter().append("[");

                    String obj = "";
                    obj += "{\"validita\": \"" + "justUsed" + "\"" + "}";

                    response.getWriter().append(obj);
                    response.getWriter().append("]");
                }
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
