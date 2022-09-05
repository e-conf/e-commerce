package Controller;

import Model.Utente;
import Model.UtenteDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "ServletLogin", value = "/ServletLogin")
public class ServletLogin extends HttpServlet {
    /*
    Questa Servlet si occupa di consentire l'accesso al proprio account.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("e-mail");
        String password = request.getParameter("psw");

        UtenteDAO utenteDAO = new UtenteDAO();
        Utente utente = new Utente();
        utente.setEmail(email);
        utente.setPassword(password);

        HttpSession session = request.getSession();
        String address;

        utente = utenteDAO.doRetrieveByEmailPassword(utente);
        //Verifica se esiste un utente nel database con quelle credenziali.
        if(utente != null) {
            //Verifica se colui che ha effettuato l'accesso ha permessi di admin.
            if(utente.isAdmin()){
                session.setAttribute("amministratore","ok");
            }else{
                session.setAttribute("amministratore","not ok");
            }
            session.setAttribute("utente", utente);
            session.setAttribute("success-login", "ok");
            address = "/WEB-INF/index.jsp";
        }
        else {
            session.setAttribute("success-login", "not ok");
            address = "/WEB-INF/loginFailed.jsp";
        }

        RequestDispatcher dispatcher =
                request.getRequestDispatcher(address);
        dispatcher.forward(request,response);
    }
}
