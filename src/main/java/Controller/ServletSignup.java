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
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "ServletSignup", value = "/ServletSignup")
public class ServletSignup extends HttpServlet {
    /*
    Questa Servlet ha come scopo quello di registrare un nuovo utente
    all'interno del database.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("name");
        String cognome = request.getParameter("surname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();

        UtenteDAO utenteDAO = new UtenteDAO();
        //Verifica che non esista un utente già registrato con quella mail.
        if(utenteDAO.doRetrieveByEmail(email) == null) {
            Pattern patternEmail = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", Pattern.CASE_INSENSITIVE);
            Matcher matcherEmail = patternEmail.matcher(email);
            boolean checkEmail = matcherEmail.find();

            Pattern patternPassword = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=?.!])(?=\\S+$).{8,30}$");
            Matcher matcherPassword = patternPassword.matcher(password);
            boolean checkPassword = matcherPassword.find();

            //Verifica che email e password rispettano la sintassi definita.
            if ((checkEmail) && (checkPassword)) {
                Utente utente = new Utente();

                utente.setNome(nome);
                utente.setCognome(cognome);
                utente.setEmail(email);
                utente.setPassword(password);

                utenteDAO.doSave(utente);

                session.setAttribute("utente", utente);
                session.setAttribute("success-login", "ok");
                response.setContentType("text/html");

                RequestDispatcher dispatcher =
                        request.getRequestDispatcher("/WEB-INF/index.jsp");
                dispatcher.forward(request, response);
            } else if ((!checkEmail) && (checkPassword)) {
                session.setAttribute("signup", "email-not-ok");
            } else if ((checkEmail) && (!checkPassword)) {
                session.setAttribute("signup", "password-not-ok");
            } else if ((!checkEmail) && (!checkPassword)) {
                session.setAttribute("signup", "email-password-not-ok");
            }
        }

    }
}
