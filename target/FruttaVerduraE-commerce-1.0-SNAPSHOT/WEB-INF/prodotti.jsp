<%@ page import="Model.Prodotto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.Carrello" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="it">
    <head>
        <title>SHOP</title>
        <link href="./css/prodotti.css" rel="stylesheet" type="text/css">
        <link rel="icon" href ="./images/logo.png" type="image/x-icon">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body>

        <%@ include file="header.jsp" %>

        <span id="title">
            <h1> PRODOTTI </h1>
        </span>

        <%
            ServletContext context = config.getServletContext();
            session = request.getSession();

            ArrayList<Prodotto> listaProdotti = (ArrayList<Prodotto>) session.getAttribute("listaProdotti");
            ArrayList<String> listaCategorie = (ArrayList<String>) context.getAttribute("listaCategorie");

        %>

        <p class="filtro"> FILTRA PER CATEGORIA:
            <select name="categoria" id="category" onchange="categoria_function(this.value)">

                <option id="idcategoria" name="categoria" value=""></option>
                <%
                    for(int i = 0; i<listaCategorie.size(); i++){
                        String c = listaCategorie.get(i);
                %>
                <option id="idcategoria" name="categoria" value="<%=c%>"><%=c.toUpperCase()%></option>
                <%
                    }
                %>
            </select>
        </p>

        <div id="productcontainer">
            <%
                int i;
                for(i=0; i<listaProdotti.size(); i++){
            %>
            <div class="gallerysection">
                <div class="gallerycontainer">
                    <img class="image" src="./images/shop/<%= listaProdotti.get(i).getImmagine()%>" alt="<%= listaProdotti.get(i).getNome()%>">
                </div>
                <div>
                    <p class="productname"> <%= listaProdotti.get(i).getNome()%> </p>
                    <p class="price"> <%= listaProdotti.get(i).getPrezzo()%>€ - <%= listaProdotti.get(i).getFormato()%> </p>
                    <p class="productorigin"> <%= listaProdotti.get(i).getProvenienza().toUpperCase()%> </p>
                    <%
                            if(listaProdotti.get(i).getQuantita() > 0){ %>
                                    <button onclick="carrelloFunction('<%= listaProdotti.get(i).getId_prodotto()%>', 1)"> AGGIUNGI AL CARRELLO <i class='fas fa-cart-arrow-down'> </i> </button>
                            <% }
                            if(listaProdotti.get(i).getQuantita() == 0) { %>
                                    <button style="background-color: red;"> PRODOTTO NON DISPONIBILE </button>
                            <% } %>
                </div>
            </div>
            <% } %>
        </div>

    </body>

</html>