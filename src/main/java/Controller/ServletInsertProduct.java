package Controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Prodotto;
import Model.ProdottoDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(name = "/ServletInsertProduct", urlPatterns = { "/insert" }, initParams = {
        @WebInitParam(name = "file-upload", value = "images/shop") })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB after which the file will be
        // temporarily stored on disk
        maxFileSize = 1024 * 1024 * 10, // 10MB maximum size allowed for uploaded files
        maxRequestSize = 1024 * 1024 * 50) // 50MB overall size of all uploaded files
public class ServletInsertProduct extends HttpServlet {
    /**
     Questa Servlet consente di inserire un nuovo prodotto, prelevando il prodotto
     da inserire dalla sessione e consentendo di inserire l'immagine.
     */
    private static final long serialVersionUID = 1L;
    static String SAVE_DIR = "";

    public void init() {
        // Get the file location where it would be stored
        SAVE_DIR = getServletConfig().getInitParameter("file-upload");
    }

    public ServletInsertProduct() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");

        out.write("Error: GET method is used but POST method is required");
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Prodotto prodotto = (Prodotto) request.getSession().getAttribute("prodottoDaInserire");

        ProdottoDAO prodottoDAO = new ProdottoDAO();

        String savePath = request.getServletContext().getRealPath("") + File.separator + SAVE_DIR;

        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        String message = "";
        if (request.getParts() != null && request.getParts().size() > 0) {
            for (Part part : request.getParts()) {
                String fileName = extractFileName(part);
                if (fileName != null && !fileName.equals("")) {
                    part.write(savePath + File.separator + fileName);
                    System.out.println(savePath + File.separator + fileName);
                    message = message + fileName;
                } else {
                    request.setAttribute("error", "Errore: Bisogna selezionare almeno un file");
                }
            }
        }

        request.setAttribute("message", message);
        request.setAttribute("id_prodottoImmagine", prodotto.getId_prodotto());

        try {
            prodottoDAO.doSave(prodotto);
            prodottoDAO.doUpdateImmagine(prodotto.getId_prodotto(), message);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        HttpSession session = request.getSession();
        ArrayList<Prodotto> listaProdotti = (ArrayList<Prodotto>) prodottoDAO.doRetrieveAll();
        session.setAttribute("listaProdotti", listaProdotti);
        session.setAttribute("idProdotto", prodotto.getId_prodotto());

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/gestioneprodotti.jsp");
        dispatcher.forward(request, response);

    }

    private String extractFileName(Part part) {
        // content-disposition: form-data; name="file"; filename="file.txt"
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }
}
