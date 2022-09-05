package Controller;

import Model.Prodotto;
import Model.ProdottoDAO;
import Model.Sconto;
import Model.ScontoDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ServletProdotti", value = "/ServletProdotti")
public class ServletProdotti extends HttpServlet {
    /*
    Questa Servlet si occupa di direzionare il flusso verso la JSP appropriata,
    a seconda del fatto che l'utente sia amministratore o meno.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProdottoDAO prodottoDAO = new ProdottoDAO();
        ArrayList<Prodotto> listaProdotti = (ArrayList<Prodotto>) prodottoDAO.doRetrieveAll();
        ArrayList<String> listaCategorie = (ArrayList<String>) prodottoDAO.doRetrieveAllCategoria();

        HttpSession session = request.getSession(true);
        String admin = (String) session.getAttribute("amministratore");
        ServletContext context = getServletContext();

        session.setAttribute("listaProdotti", listaProdotti);
        context.setAttribute("listaCategorie", listaCategorie);

        String address;
        //Verifichiamo se l'utente è admin, se sì è stato impostato un attributo nella sessione in fase di login.
        if(admin != null) {
            if (admin.equalsIgnoreCase("ok")) {
                ScontoDAO scontoDAO = new ScontoDAO();
                ArrayList<Sconto> listaSconti = (ArrayList<Sconto>) scontoDAO.doRetrieveAll();
                session.setAttribute("listaSconti", listaSconti);
                address = "/WEB-INF/gestioneprodotti.jsp";
            }
            else
                address = "/WEB-INF/prodotti.jsp";
        }else
            address = "/WEB-INF/prodotti.jsp";

        RequestDispatcher dispatcher =
                request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
