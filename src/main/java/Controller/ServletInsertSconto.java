package Controller;

import Model.Sconto;
import Model.ScontoDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "ServletInsertSconto", value = "/ServletInsertSconto")
public class ServletInsertSconto extends HttpServlet {
    /*
    Questa Servlet ha come scopo quello di inserire un nuovo sconto all'interno
    del database.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codice = request.getParameter("codice");
        Integer percentuale = Integer.parseInt(request.getParameter("percentuale"));
        String data_fine_sconto = request.getParameter("date_coupon");
        Date data_coupon = null;
        try {
            data_coupon = new SimpleDateFormat("yyyy-MM-dd").parse(data_fine_sconto);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ScontoDAO scontoDAO = new ScontoDAO();
        Sconto sconto = new Sconto();

        sconto.setCodiceSconto(codice);
        sconto.setPercentualeSconto(percentuale);
        sconto.setDataFineSconto(data_coupon);

        HttpSession session = request.getSession();
        //Verifica che non esista già uno sconto memorizzato con quel codice.
        if(scontoDAO.doRetrieveScontoById(sconto.getCodiceSconto()) == null) {
            scontoDAO.doSave(sconto);
            ArrayList<Sconto> listaSconti = (ArrayList<Sconto>) scontoDAO.doRetrieveAll();
            session.setAttribute("listaSconti", listaSconti);
        }

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/gestioneprodotti.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
