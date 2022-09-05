package Controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "ServletLogout", value = "/ServletLogout")
public class ServletLogout extends HttpServlet {
    /*
    Questa Servlet ha come scopo quello di consentire il logout di utente.
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        session.removeAttribute("utente");
        session.removeAttribute("success-login");
        session.removeAttribute("amministratore");

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/index.jsp");
        dispatcher.forward(request,response);
    }
}
