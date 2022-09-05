package Controller;

import Model.Sconto;
import Model.ScontoDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ServletVerificaValiditaCodiceSconto", value = "/ServletVerificaValiditaCodiceSconto")
public class ServletVerificaValiditaCodiceSconto extends HttpServlet {
    @Override
    /*
    Questa Servlet verifica, in fase di inserimento di un nuovo sconto,
    se il codice sconto inserito dall'admin è valido, cioè non è già presente
    nel database.
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codice = request.getParameter("codice");

        ScontoDAO scontoDAO = new ScontoDAO();
        if(scontoDAO.doRetrieveScontoById(codice) == null){
            response.getWriter().append("[");

            String obj = "";
            obj += "{\"flag\": \"" + "true" + "\"" +
                    "}";

            response.getWriter().append(obj);
            response.getWriter().append("]");
        } else {
            response.getWriter().append("[");

            String obj = "";
            obj += "{\"flag\": \"" + "false" + "\"" +
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
