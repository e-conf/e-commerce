<%@ page import="Model.Prodotto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.Sconto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="it">
    <head>
        <title>GESTIONE PRODOTTI</title>
        <link href="./css/gestioneprodotti.css" rel="stylesheet" type="text/css">
        <link rel="icon" href ="./images/logo.png" type="image/x-icon">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body>

        <%@ include file="header.jsp" %>

        <% ArrayList<Prodotto> listaProdotti = (ArrayList<Prodotto>) session.getAttribute("listaProdotti"); %>

        <div id="newProduct">
            <div>
                <form action="ServletInsertData">
                    <button type="submit" style="background-color: blue">
                        INSERISCI PRODOTTO: <i class='fas fa-upload'></i>
                    </button>
                </form>
            </div>
            <div>
                <button type="submit" onclick="inserisciDatiCoupon()" style="background-color: blue">
                    INSERISCI SCONTO: <i class='fas fa-hand-holding-usd'></i>
                </button>
            </div>
            <div>
                <a href="ServletGestisciSconti">
                    <button type="button" onclick="getElementById('gestioneCouponForm').style.display = 'block';" style="background-color: blue">
                        GESTISCI SCONTI: <i class='fas fa-search-dollar'></i>
                    </button>
                </a>
            </div>
        </div>

        <br>

        <div id="couponForm" class="popup">
            <form name="couponForm" class="popup-content animate" action="ServletInsertSconto" method="post" onsubmit="return validateCodiceSconto();">
                <div>
                    <div class="container" id="campiCouponContainer">
                        <fieldset>
                            <legend> COUPON: </legend>
                            <i class='fas fa-search-dollar'> CODICE </i> <input type="text" id="codice" name="codice" maxlength="5" required onblur="controlloCodiceDatabase(this.value)"> <br>
                            <i class='fas fa-percentage'> PERCENTUALE </i> <input type="number" id="percentuale" name="percentuale" min="1" max="99" onkeyup="if(this.value > 99) this.value = 99;" required> <br>
                            <i class='fas fa-hourglass-start'> DATA FINE COUPON </i> <input type="date" id="date_coupon" name="date_coupon" required>
                        </fieldset>
                        <button type="submit" id="inserisciCoupon">INSERISCI COUPON</button>
                    </div>
                </div>
                <div class="container">
                    <button type="button" onclick="document.getElementById('couponForm').style.display='none'" class="cancelbtn">Chiudi</button>
                    <button type="reset" class="cancelbtn">Pulisci</button>
                </div>
            </form>
        </div>

        <div id="tableContainer">
            <table>
                <tr>
                    <th> CODICE PRODOTTO </th>
                    <th> NOME  </th>
                    <th> PROVENIENZA  </th>
                    <th> DESCRIZIONE  </th>
                    <th> QUANTITÀ PRODOTTO </th>
                    <th> PREZZO  </th>
                    <th> FORMATO VENDITA </th>
                    <th> CATEGORIA  </th>
                    <th> IMMAGINE </th>
                </tr>
                <% for(int i=0; i<listaProdotti.size(); i++){ %>
                <tr>
                    <td> <%= listaProdotti.get(i).getId_prodotto()%>
                        <form action="ServletDeleteProduct" onsubmit="return confirm('Vuoi cancellare il prodotto dal database?');">
                            <input type="hidden" name="id_prodotto" value="<%=listaProdotti.get(i).getId_prodotto()%>">
                            <button type="submit" class="modificaBtn">
                                <i class='fas fa-trash'> </i>
                            </button>
                        </form>
                        <form action="ServletModificaDati" method="POST">
                            <input type="hidden" name="id_prodotto" value="<%=listaProdotti.get(i).getId_prodotto()%>">
                            <input type="hidden" name="nomeProdotto" value="<%=listaProdotti.get(i).getNome()%>">
                            <input type="hidden" name="provenienzaProdotto" value="<%=listaProdotti.get(i).getProvenienza()%>">
                            <input type="hidden" name="descrizioneProdotto" value="<%=listaProdotti.get(i).getDescrizione()%>">
                            <input type="hidden" name="quantitaProdotto" value="<%=listaProdotti.get(i).getQuantita()%>">
                            <input type="hidden" name="prezzoProdotto" value="<%=listaProdotti.get(i).getPrezzo()%>">
                            <input type="hidden" name="formatoVenditaProdotto" value="<%=listaProdotti.get(i).getFormato()%>">
                            <input type="hidden" name="categoriaProdotto" value="<%=listaProdotti.get(i).getCategoria()%>">
                            <input type="hidden" name="immagineProdotto" value="<%=listaProdotti.get(i).getImmagine()%>">
                            <button class="modificaBtn"> <i class='fas fa-pencil-alt' id="updateName"> </i> </button>
                        </form>
                    </td>
                    <td> <%= listaProdotti.get(i).getNome()%> </td>
                    <td> <%= listaProdotti.get(i).getProvenienza()%> </td>
                    <td> <%= listaProdotti.get(i).getDescrizione()%> </td>
                    <td> <%= listaProdotti.get(i).getQuantita()%> </td>
                    <td> <%= listaProdotti.get(i).getPrezzo()%> </td>
                    <td> <%= listaProdotti.get(i).getFormato()%> </td>
                    <td> <%= listaProdotti.get(i).getCategoria()%> </td>

                    <% String message = (String) request.getAttribute("message"); %>
                    <% String idProdottoImmagine = (String) request.getAttribute("id_prodottoImmagine"); %>
                    <% if(message != null && idProdottoImmagine.equalsIgnoreCase(listaProdotti.get(i).getId_prodotto())){ %>
                    <td>
                        <div class="imageContainer">
                            <img class="prodotto" src="./images/shop/<%= message%>">
                            <form action="ServletUpdateImageRedirect">
                                <input type="hidden" name="id_prodotto" value="<%=idProdottoImmagine%>">
                                <input type="hidden" name="immagine" value="<%=message%>">
                                <button class="buttonImage"> CAMBIA <i class='fas fa-wrench'> </i> </button>
                            </form>
                        </div>
                    </td>
                    <% } else { %>
                    <td>
                        <div class="imageContainer">
                            <img class="prodotto" src="./images/shop/<%= listaProdotti.get(i).getImmagine()%>">
                            <form action="ServletUpdateImageRedirect">
                                <input type="hidden" name="id_prodotto" value="<%=listaProdotti.get(i).getId_prodotto()%>">
                                <input type="hidden" name="immagine" value="<%=listaProdotti.get(i).getImmagine()%>">
                                <button class="buttonImage"> CAMBIA <i class='fas fa-wrench'> </i> </button>
                            </form>
                        </div>
                    </td>
                    <% } %>
                </tr>
                <% } %>
            </table>
        </div>

    </body>
</html>

