package Controller;

import Model.Prodotto;
import Model.ProdottoDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/index.html", loadOnStartup = 0)
public class ServletInit extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            ProdottoDAO prodottoDAO = new ProdottoDAO();
            ArrayList<Prodotto> listaProdotti = (ArrayList<Prodotto>) prodottoDAO.doRetrieveAll();
            request.getSession().setAttribute("listaProdotti", listaProdotti);

            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("/WEB-INF/index.jsp");
            dispatcher.forward(request, response);
        }
}
