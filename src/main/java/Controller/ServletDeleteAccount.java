package Controller;

import Model.UtenteDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletDeleteAccount", value = "/ServletDeleteAccount")
public class ServletDeleteAccount extends HttpServlet {
    /*
    Questa Servlet ha come scopo quello di eliminare un account dal database.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id_utente = Integer.parseInt(request.getParameter("id"));

        UtenteDAO utenteDAO = new UtenteDAO();
        try {
            utenteDAO.doDeleteUser(id_utente);
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("/ServletLogout");
            dispatcher.forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
