<%@ page import="Model.Prodotto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>INSERIMENTO DATI</title>
        <link rel="icon" href ="./images/logo.png" type="image/x-icon">
        <link href="./css/gestioneprodotti.css" rel="stylesheet" type="text/css">
    </head>
    <body>

        <%@include file="header.jsp"%>

        <div id="insertProduct">
            <form action="ServletInsertRetrieveData" method="POST">
                <div class="container">
                    <label for="cod"><b>Codice prodotto:</b></label>
                    <input type="text" id="cod" name="cod" required>
                    </p>
                    <label for="nome"><b>Nome: </b></label>
                    <input type="text" id="nome" name="nome" required>

                    <label for="provenienza"><b>Provenienza: </b></label>
                    <input type="text" id="provenienza" name="provenienza" required>

                    <label for="descrizione"><b>Descrizione</b></label> </p>
                    <input type="text" id="descrizione" name="descrizione" cols="60" rows="10">
                    <p></p>
                    <label for="quantita"><b>Quantità:</b></label>
                    <input type="text" id="quantita" name="quantita" required>
                    <p></p>
                    <label for="prezzo"><b>Prezzo:</b></label>
                    <input type="text" id="prezzo" name="prezzo" required>

                    <label for="categoria"><b>Categoria:</b></label>
                    <input type="text" id="categoria" name="categoria" required>

                    <label for="formato"><b>Formato:</b></label>
                    <input type="text" id="formato" name="formato" required>

                    <button type="submit">INSERISCI!</button>
                </div>
            </form>
            <a class="icon" href="javascript:history.go(-1)"> <i class="fa fa-reply"> </i> </a>
        </div>
    </body>
</html>
