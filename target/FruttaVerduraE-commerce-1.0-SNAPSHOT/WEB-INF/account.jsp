<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.*" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="it">
    <head>
        <title>CONFrutta&Verdura</title>
        <link href="./css/account.css" rel="stylesheet" type="text/css">
        <link rel="icon" href ="./images/logo.png" type="image/x-icon">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script>
            $(document).ready(function(){
                $("#cambioPassword").click(function(){
                    $("#cambioPasswordForm").toggle();
                });
            });
        </script>

    </head>
    <body>

        <%@ include file="header.jsp"%>

        <%
            Utente utente = (Utente) session.getAttribute("utente");
            ArrayList<OrdineProdotto> listaOrdineProdotto = (ArrayList<OrdineProdotto>) session.getAttribute("listaOrdineProdotto");
            ArrayList<Ordine> listaOrdiniUtente = (ArrayList<Ordine>) session.getAttribute("listaOrdini");

            if(utente != null){
        %>
            <div class="row">
            <% if(!utente.isAdmin()){ %>
                <div class="column" id="orders">
                    <div class="card">
                        <p class="title"> I miei ordini  <i class='far fa-folder-open'> </i>  </p>
                            <table>
                                <tr>
                                    <th> <i class='fas fa-shopping-bag'> </i> ORDINI EFFETTUATI: </th>
                                    <td> <%=listaOrdiniUtente.size()%> </td>
                                </tr>
                                <tr>
                                    <th> <i class='fab fa-cc-mastercard'> </i> SPESA TOTALE: </th>
                                    <td> &#8364; <%=request.getSession().getAttribute("spesaTotaleUtente")%> </td>
                                </tr>
                            </table>
                        <% if(listaOrdiniUtente.size() > 0) {
                            for(int i = 0; i < listaOrdiniUtente.size(); i++){
                        %>
                                <div class="accordion">
                                    <button class="acc-label" onclick="showDetails('<%=listaOrdiniUtente.get(i).getId_ordine()%>', '<%=i%>')"> ORDINE <%=i+1%> </button>
                                    <div class="acc-content" id="details<%=i%>"> </div>
                                    <div class="acc-content" id="moreInfo<%=i%>" style="display: none"> </div>
                                </div>
                        <% }
                        } else { %>
                            <p> Nessun ordine è stato ancora effettuato.</p>
                            <p> <a href="ServletProdotti"> Corri ad effettuare il tuo primo ordine. <i class='fas fa-cart-plus'> </i> </a> </p>
                        <% } %>
                    </div>
                </div>

                <% } else { %>

                <div class="column" id="orders">
                    <div class="card">
                        <p class="title"> App info  <i class='far fa-folder-open'> </i>  </p>
                        <table>
                            <tr>
                                <th> <i class='fab fa-cc-mastercard'> </i> NUMERO UTENTI REGISTRATI: </th>
                                <td> <%=session.getAttribute("numeroUtentiRegistrati")%> </td>
                            </tr>
                            <tr>
                                <th> <i class='fas fa-shopping-bag'> </i> NUMERO ORDINI EFFETTUATI: </th>
                                <td> <%=session.getAttribute("numeroOrdini")%> </td>
                            </tr>
                            <tr>
                                <th> <i class='fab fa-cc-mastercard'> </i> RICAVO TOTALE: </th>
                                <td> &#8364;<%=session.getAttribute("spesaTotale")%> </td>
                            </tr>
                            <tr>
                                <th> <i class='fas fa-apple-alt'> </i> NUMERO PRODOTTI SUL MERCATO: </th>
                                <td> <%ArrayList<Prodotto> listaProdotti = (ArrayList<Prodotto>) session.getAttribute("listaProdotti");%> <%=listaProdotti.size()%></td>
                            </tr>
                            <% HashMap<String, Integer> prodottoBestSelling = (HashMap<String, Integer>) session.getAttribute("prodottoPiuVenduto");
                               if(prodottoBestSelling != null){
                            %>
                            <tr>
                                <th> <i class='fas fa-cart-arrow-down'> </i> PRODOTTO PI&Ugrave; VENDUTO: </th>
                                <td> <%=prodottoBestSelling.toString().split("=")[0].substring(1)%> <%=prodottoBestSelling.toString().split("=")[1].replace("}", "")%></td>
                            </tr>
                            <% } %>
                            <% HashMap<String, Integer> prodottoMostRemunerative = (HashMap<String, Integer>) session.getAttribute("prodottoPiuRemunerativo");
                                if(prodottoMostRemunerative != null){
                            %>
                            <tr>
                                <th> <i class='fab fa-cc-visa'> </i> PRODOTTO PI&Ugrave; REMUNERATIVO: </th>
                                <td> <%=prodottoMostRemunerative.toString().split("=")[0].substring(1)%> &#8364;<%=prodottoMostRemunerative.toString().split("=")[1].replace("}", "")%></td>
                            </tr>
                            <% } %>
                        </table>
                    </div>
                </div>

            <% } %>

                <div class="column">
                    <div class="card">
                        <% if(utente.isAdmin()) { %>
                        <p class="title"> Amministratore <i class='fas fa-user-lock'> </i> </p>
                        <% } else { %>
                        <p class="title"> Il mio profilo <i class='far fa-address-card'> </i>  </p>
                        <% } %>
                        <table>
                            <tr>
                                <th> <i class='fas fa-user-circle'> </i> NOME: </th>
                                <td> <%= utente.getNome() %> </td>
                            </tr>
                            <tr>
                                <th> <i class='far fa-user-circle'> </i> COGNOME: </th>
                                <td> <%= utente.getCognome() %> </td>
                            </tr>
                            <tr>
                                <th> <i class='far fa-envelope'> </i> EMAIL: </th>
                                <td> <%= utente.getEmail() %> </td>
                            </tr>
                        </table>

                        <button id="cambioPassword"> CAMBIA PASSWORD <i class='fas fa-fingerprint'> </i> </button>
                        <div id="cambioPasswordForm" style="display: none;">
                            <form name="cambioPasswordForm" class="popup-content animate" action="ServletCambioPassword" method="post" onsubmit="return verificaCambioPassword();">
                                <div class="container">
                                    <input type="hidden" name="email" value="<%=utente.getEmail()%>">

                                    <label for="vecchiaPsw"><b>PASSWORD CORRENTE</b></label>
                                    <input type="password" id="vecchiaPsw" name="vecchiaPsw" required minlength="8">

                                    <label for="nuovaPsw"><b>NUOVA PASSWORD</b></label>
                                    <input type="password" id="nuovaPsw" name="nuovaPsw" required minlength="8">

                                    <label for="confermaNuovaPsw"><b>CONFERMA NUOVA PASSWORD</b></label>
                                    <input type="password" id="confermaNuovaPsw" name="confermaNuovaPsw" required minlength="8">

                                    <button type="submit">CAMBIA</button>
                                </div>

                                <div class="container">
                                    <button type="button" onclick="document.getElementById('cambioPasswordForm').style.display='none'" class="cancelbtn">Chiudi</button>
                                    <button type="reset" class="cancelbtn">Pulisci</button>
                                </div>
                            </form>
                        </div>
                        <form action="ServletDeleteAccount" method="get">
                            <input type="hidden" name="id" value="<%=utente.getId()%>">
                            <button onclick="return confirm('Vuoi eliminare il tuo account?')" style="background-color: red;"> ELIMINA ACCOUNT <i class='fas fa-ban'> </i> </button>
                        </form>
                    </div>
                </div>
            </div>

        <% } %>

    </body>
</html>