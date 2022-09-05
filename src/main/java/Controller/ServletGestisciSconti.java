package Controller;

import Model.Sconto;
import Model.ScontoDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ServletGestisciSconti", value = "/ServletGestisciSconti")
public class ServletGestisciSconti extends HttpServlet {
    /*
    Questa Servlet ha come scopo dirigere il flusso verso la JSP di gestione
    degli sconti, riservata all'amministratore.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ScontoDAO scontoDAO = new ScontoDAO();
        HttpSession session = request.getSession();
        ArrayList<Sconto> listaSconti = new ArrayList<Sconto>();

        listaSconti = (ArrayList<Sconto>) session.getAttribute("listaSconti");
        if(listaSconti == null){
            listaSconti = (ArrayList<Sconto>) scontoDAO.doRetrieveAll();
            session.setAttribute("listaSconti", listaSconti);
        }

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/gestionesconti.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
