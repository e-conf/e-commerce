package Controller;

import Model.Prodotto;
import Model.ProdottoDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ServletDeleteProduct", value = "/ServletDeleteProduct")
public class ServletDeleteProduct extends HttpServlet {
    /*
    Questa Servlet ha come scopo eliminare un prodotto dal database.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id_prodotto = request.getParameter("id_prodotto");

        ProdottoDAO prodottoDAO = new ProdottoDAO();

        try {
            prodottoDAO.doDeleteProduct(id_prodotto);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayList<Prodotto> listaProdotti = (ArrayList<Prodotto>) prodottoDAO.doRetrieveAll();
        HttpSession session = request.getSession();
        session.setAttribute("listaProdotti", listaProdotti);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/gestioneprodotti.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
