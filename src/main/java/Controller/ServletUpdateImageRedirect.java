package Controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ServletUpdateImageRedirect", value = "/ServletUpdateImageRedirect")
public class ServletUpdateImageRedirect extends HttpServlet {
    /*
    Questa Servlet ha come scopo quello di prendere i parametri necessari
    all'aggiornamento dell'immagine di un prodotto e di indirizzare il flusso
    verso la JSP appropriata.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id_prodotto = request.getParameter("id_prodotto");
        String immagine = request.getParameter("immagine");
        request.getSession().setAttribute("idImmagineProdotto", id_prodotto);
        request.getSession().setAttribute("immagine", immagine);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/updateImage.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
