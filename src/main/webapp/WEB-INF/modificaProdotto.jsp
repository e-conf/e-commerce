<%@ page import="Model.Prodotto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="it">
    <head>
        <title>MODIFICA PRODOTTO</title>
        <link href="./css/modificaSuccesso.css" rel="stylesheet" type="text/css">
        <link rel="icon" href ="./images/logo.png" type="image/x-icon">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body>

        <%@ include file="header.jsp"%>

        <% Prodotto prodotto = (Prodotto) request.getSession().getAttribute("prodottoDaModificare"); %>

        <div id="updateProduct">
            <div id="updateImage">
                <img class="prodotto" src="./images/shop/<%=prodotto.getImmagine()%>">
                <form action="ServletUpdateImageRedirect">
                    <input type="hidden" name="id_prodotto" value="<%=prodotto.getId_prodotto()%>">
                    <input type="hidden" name="immagine" value="<%=prodotto.getImmagine()%>">
                    <button class="buttonImage"> CAMBIA <i class='fas fa-wrench'> </i> </button>
                </form>
            </div>
            <form action="ServletModificaProdotto" method="POST">
                <label for="cod"><b>Codice prodotto: <%=prodotto.getId_prodotto()%> </b></label>
                <input type="hidden" id="cod" name="cod" value="<%=prodotto.getId_prodotto()%>">
                </p>
                <label for="nome"><b>Nome: </b></label> </p>
                <input type="text" id="nome" name="nome" value="<%=prodotto.getNome()%>">
                </p>
                <label for="provenienza"><b>Provenienza: </b></label> </p>
                <input type="text" id="provenienza" name="provenienza" value="<%=prodotto.getProvenienza()%>">
                </p>
                <label for="descrizione"><b>Descrizione: </b></label> </p>
                <input type="text" id="descrizione" name="descrizione" cols="60" rows="10" value="<%=prodotto.getDescrizione()%>">
                </p>
                <label for="quantita"><b>Quantità:</b></label>
                <input type="text" id="quantita" name="quantita" value="<%=prodotto.getQuantita()%>">
                </p>
                <label for="prezzo"><b>Prezzo:</b></label>
                <input type="text" id="prezzo" name="prezzo" value="<%=prodotto.getPrezzo()%>">

                <label for="categoria"><b>Categoria:</b></label>
                <input type="text" id="categoria" name="categoria" value="<%=prodotto.getCategoria()%>">

                <label for="formato"><b>Formato:</b></label>
                <input type="text" id="formato" name="formato" value="<%=prodotto.getFormato()%>">

                <input type="hidden" name="image">

                <button type="submit"> MODIFICA </button>
                <a class="icon" href="javascript:history.go(-1)"> <i class="fa fa-reply"> </i> </a>
            </form>
        </div>
    </body>
</html>
