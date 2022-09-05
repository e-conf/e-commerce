package Controller;

import Model.Sconto;
import Model.ScontoDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ServletDeleteScontoDatabase", value = "/ServletDeleteScontoDatabase")
public class ServletDeleteScontoDatabase extends HttpServlet {
    /*
    Questa Servlet consente all'amministratore di eliminare uno sconto precedentemente
    inserito.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codice = request.getParameter("codice");

        ScontoDAO scontoDAO = new ScontoDAO();
        try {
            Sconto sconto = scontoDAO.doRetrieveScontoById(codice);
            scontoDAO.doDeleteSconto(codice);
            ArrayList<Sconto> listaSconti = (ArrayList<Sconto>) scontoDAO.doRetrieveAll();
            request.getSession().setAttribute("listaSconti", listaSconti);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
