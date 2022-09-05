<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title> RIEPILOGO ORDINE </title>
        <link href="./css/riepilogoOrdine.css" rel="stylesheet" type="text/css">
        <link rel="icon" href ="./images/logo.png" type="image/x-icon">
    </head>
    <body>

        <%@include file="header.jsp"%>

        <% Ordine ordine = (Ordine) session.getAttribute("ordineEffettuato"); %>
        <% ArrayList<OrdineProdotto> listaOrdineProdotto = (ArrayList<OrdineProdotto>) session.getAttribute("listaOrdineProdotto"); %>
        <% Utente utente = (Utente) session.getAttribute("clienteOrdine"); %>
        <% ArrayList<Prodotto> listaProdotti = (ArrayList<Prodotto>) session.getAttribute("listaProdotti"); %>
        <% if((ordine != null)&&(listaOrdineProdotto != null)&&(utente != null)&&(listaProdotti != null)){ %>
        <h3 style="text-align: center;"> ORDINE EFFETTUATO CON SUCCESSO </h3>
        <div id="riepilogoOrdineContainer">
            <fieldset>
                <legend> Riepilogo Ordine </legend>
                <span> ID: <%= ordine.getId_ordine() %> </span> <br>
                <span> DATA: <%= ordine.getData_ordine() %> </span> <br>
                <%  double importoNonScontato = (double) session.getAttribute("importoNonScontato");
                    double importoScontato = ordine.getImporto();
                    if(importoNonScontato != importoScontato){ %>
                <span> IMPORTO: <span id="importoIniziale" style="color: red; text-decoration-line: line-through;"> &#8364;<%=importoNonScontato%> </span> &#8364;<%=importoScontato%> </span> <br>
                    <% } else { %>
                <span> IMPORTO: &#8364; <%= ordine.getImporto() %> </span> <br>
                <% } %>

                <legend> Prodotti </legend>
                <% for(int i = 0; i < listaOrdineProdotto.size(); i++) { %>
                <div id="prodottoDati">
                    <div id="left">
                        <img class="image" src="./images/shop/<%=listaOrdineProdotto.get(i).getImmagine_prodotto()%>" alt="<%= listaOrdineProdotto.get(i).getImmagine_prodotto()%>">
                    </div>
                    <div id="right">
                        <table>
                            <tr>
                                <th> ID PRODOTTO: </th>
                                <td> <%= listaOrdineProdotto.get(i).getId_prodotto()%> </td>
                            </tr>
                            <tr>
                                <th> QUANTITÀ: </th>
                                <td> <%= listaOrdineProdotto.get(i).getQuantita_prodotto()%> </td>
                            </tr>
                            <tr>
                                <th> PREZZO PRODOTTO: </th>
                                <td> &#8364; <%= listaOrdineProdotto.get(i).getPrezzo_prodotto()%> </td>
                            </tr>
                            <tr>
                                <th> TOTALE: </th>
                                <td> &#8364; <%= listaOrdineProdotto.get(i).getTotale_prodotto()%> </td>
                            </tr>
                        </table>
                    </div>
                </div>
                <% } %>

                <legend class="indirizzoConsegna"> Indirizzo di consegna </legend>
                <span class="indirizzoConsegna"> VIA: <%= ordine.getVia() %> </span> <br>
                <span class="indirizzoConsegna"> NUM: <%= ordine.getNumero_civico() %> </span> <br>
                <span class="indirizzoConsegna"> CAP: <%= ordine.getCap() %> </span> <br>
                <legend class="indirizzoConsegna"> Cliente </legend>
                <span class="indirizzoConsegna"> ID: <%= ordine.getId_utente() %> </span> <br>
                <span class="indirizzoConsegna"> EMAIL: <%= utente.getEmail() %> </span> <br>
                <span class="indirizzoConsegna"> NOME: <%= utente.getNome() %> </span> <br>
                <span class="indirizzoConsegna"> COGNOME: <%= utente.getCognome() %> </span> <br>
            </fieldset>

            <button id="buttonStampa" onclick="stampa()"> STAMPA ORDINE! <i class="fa fa-print"> </i> </button>
        </div>
        <% } else { %>
            <h1 class="error"> Il carrello è stato svuotato. Corri a riempirlo. </h1>
            <p class="error">
            <a href="ServletCarrelloRedirect" class="error"> Carrello<i class="fas fa-shopping-cart"> </i> </a> <br>
            <a href="ServletHome"> Home-page <i class="fa fa-home"> </i> </a>
            </p>
        <% } %>

    </body>
</html>
