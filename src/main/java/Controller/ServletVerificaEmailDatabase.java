package Controller;

import Model.UtenteDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ServletVerificaEmailDatabase", value = "/ServletVerificaEmailDatabase")
public class ServletVerificaEmailDatabase extends HttpServlet {
    /*
    Questa Servlet ha come scomo quello di andare a verificare se l'email
    inserita in fase di login sia valida, cioè già presente nel database.
    Viceversa, in fase di signup, verifica la cosa inversa, cioè che non
    esista un'email uguale a quella inserita, all'interno del database.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");

        UtenteDAO utenteDAO = new UtenteDAO();
        if(utenteDAO.doRetrieveByEmail(email) == null){
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
