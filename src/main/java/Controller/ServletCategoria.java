package Controller;

import Model.Prodotto;
import Model.ProdottoDAO;
import com.mysql.cj.xdevapi.JsonArray;
import com.mysql.cj.xdevapi.JsonValue;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

@WebServlet(name = "ServletCategoria", value = "/ServletCategoria")
public class ServletCategoria extends HttpServlet {
    /*
    Questa Servlet ci consente di filtrare tutti i prodotti che appartengono
    ad una determinata categoria che viene selezionata dall'utente.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoria = request.getParameter("categoria");

        ProdottoDAO prodottoDAO = new ProdottoDAO();

        ArrayList<Prodotto> prodottiFiltrati = new ArrayList<>();

        if(categoria.equals(""))
            prodottiFiltrati = (ArrayList<Prodotto>) prodottoDAO.doRetrieveAll();
        else
            prodottiFiltrati = (ArrayList<Prodotto>) prodottoDAO.doRetrieveByCategoria(categoria);

        int cont = prodottiFiltrati.size();

        for(int j=0; j<cont; j++) {
            System.out.println(prodottiFiltrati.get(j).toString());
        }
        String obj = "";
        if(prodottiFiltrati != null){
            response.setContentType("text/plain;charset=UTF-8");
            response.getWriter().append("[");

            for(int i=0; i<cont-1; i++) {
                obj += "{\"nome\": \"" + prodottiFiltrati.get(i).getNome() + "\"," +
                        "\"id\": \"" + prodottiFiltrati.get(i).getId_prodotto() + "\"," +
                        "\"immagine\": \"" + prodottiFiltrati.get(i).getImmagine() + "\"," +
                        "\"provenienza\": \"" + prodottiFiltrati.get(i).getProvenienza() + "\"," +
                        "\"prezzo\": \"" + prodottiFiltrati.get(i).getPrezzo() + "\"," +
                        "\"formato\": \"" + prodottiFiltrati.get(i).getFormato() + "\"," +
                        "\"quantita\": \"" + prodottiFiltrati.get(i).getQuantita() + "\"" +
                        "},";
            }

            obj += "{\"nome\": \"" + prodottiFiltrati.get(cont-1).getNome() + "\"," +
                    "\"id\": \"" + prodottiFiltrati.get(cont-1).getId_prodotto() + "\"," +
                    "\"immagine\": \"" + prodottiFiltrati.get(cont-1).getImmagine() + "\"," +
                    "\"provenienza\": \"" + prodottiFiltrati.get(cont-1).getProvenienza() + "\"," +
                    "\"prezzo\": \"" + prodottiFiltrati.get(cont-1).getPrezzo() + "\"," +
                    "\"formato\": \"" + prodottiFiltrati.get(cont-1).getFormato() + "\"," +
                    "\"quantita\": \"" + prodottiFiltrati.get(cont-1).getQuantita() + "\"" +
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
