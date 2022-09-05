package Controller;

import Model.Utente;
import Model.UtenteDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "ServletCambioPassword", value = "/ServletCambioPassword")
public class ServletCambioPassword extends HttpServlet {
    /*
    Questa Servlet consente ad un utente di cambiare la propria password.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String vecchiaPassword = request.getParameter("vecchiaPsw");
        String nuovaPassword = request.getParameter("nuovaPsw");

        Utente utente = new Utente();
        utente.setEmail(email);
        utente.setPassword(vecchiaPassword);

        UtenteDAO utenteDAO = new UtenteDAO();
        Utente utente1 = utenteDAO.doRetrieveByEmailPassword(utente);

        //Verifichiamo se la password da cambiare è dell'utente in questione.
        if(utente1 == null){
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("/WEB-INF/loginFailed.jsp");
            dispatcher.forward(request, response);
        } else {

            utente1.setPassword(nuovaPassword);
            try {
                utenteDAO.doDeleteUser(utente1.getId());
                utenteDAO.doSaveChangePassword(utente1);

                RequestDispatcher dispatcher =
                        request.getRequestDispatcher("/WEB-INF/index.jsp");
                dispatcher.forward(request, response);

            } catch (SQLException e) {
                RequestDispatcher dispatcher =
                        request.getRequestDispatcher("/WEB-INF/loginFailed.jsp");
                dispatcher.forward(request, response);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
