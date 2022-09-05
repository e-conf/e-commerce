<html>
    <head>
      <title>CARRELLO</title>
      <link href="./css/carrello.css" rel="stylesheet" type="text/css">
      <link rel="icon" href ="./images/logo.png" type="image/x-icon">
    </head>

    <body>

      <%@ include file="header.jsp" %>

      <div id="carrello">
          <h1> CARRELLO </h1>
          <% Carrello carrello = (Carrello) session.getAttribute("carrello");
             Utente utente = (Utente) session.getAttribute("utente");

            if(carrello != null && carrello.getListaProdotti().size() > 0){
            for(int i = 0; i < carrello.getAllProduct().size(); i++){
          %>
          <div id="productBox<%=i%>">
              <div id="productInfo">
                <span id="info">
                  <%= carrello.getAllProduct().get(i).getNome() %>
                  &#8364; <%= carrello.getAllProduct().get(i).getPrezzo() %> <em> -
                  <%= carrello.getAllProduct().get(i).getFormato() %> </em>
                </span>
              </div>
            <div id="productInput">
              <input type="number" id="quantita<%=i%>" class="quantita" name="quantita" value=<%= carrello.getQuantity(carrello.getAllProduct().get(i))%> min="1" max="<%=carrello.getAllProduct().get(i).getQuantita()%>" onkeyup="if(this.value > <%=carrello.getAllProduct().get(i).getQuantita()%>) this.value = <%=carrello.getAllProduct().get(i).getQuantita()%>;" onchange="increaseQuantity('<%=carrello.getAllProduct().get(i).getId_prodotto()%>', '<%=carrello.getAllProduct().get(i).getPrezzo()%>', this.value, '<%= i %>')">
            </div>
            <div id="totalProduct">
              <span id="totale<%=i%>"> <b> TOTALE: </b> &#8364; <%= carrello.totaleProdotto(carrello.getAllProduct().get(i))%> </span>
            </div>
            <div id="deleteProduct">
              <a onclick="deleteProductCart('<%=carrello.getAllProduct().get(i).getId_prodotto()%>', '<%=i%>')" style="color: red;">
                <i class='fas fa-trash'> </i>
              </a>
            </div>
          </div>

          <% } %>

            <span id="totaleSpesa"> TOTALE: <%= carrello.totaleCarrello() %> &#8364; </span>
            <% if(utente != null){ %>
            <span id="checkout"> <button style="background-color: orangered;" onclick="document.getElementById('ordineForm').style.display='block'"> PROCEDI </button> </span>
            <% }else { %>
            <span id="checkout"> <button style="background-color: orange; opacity: 1" onclick="apriForm()"> EFFETTUA LOGIN </button> </span>
            <% }
          } else { %>
            <p class="noProductInfo" style="text-align: center"> NESSUN PRODOTTO PRESENTE NEL CARRELLO </p>
            <% } %>
      </div>




      <% if(utente != null && carrello != null){ %>
      <div id="ordineForm" class="popup">
        <form name="formOrdine" class="popup-content animate" action="ServletEffettuaOrdine" method="post">

          <div class="imgcontainer">
            <img src="images/map.jpg" alt="map" class="avatar">
          </div>

          <div>
            <div class="container" id="container">
              <fieldset>
                <legend> INDIRIZZO DI SPEDIZIONE: </legend>
                <i class='fas fa-road'> VIA </i> <input type="text" id="via" name="via" required> <br>
                <i class='fas fa-route'> NUM </i> <input type="text" id="numero" name="numero" required> <br>
                <i class='fas fa-thumbtack'> CAP </i> <input type="text" id="cap" name="cap" required>
              </fieldset>
              <fieldset>
                <legend> SCONTO: </legend>
                <i class='fas fa-gift'> COUPON </i> <br>
                <input type="text" id="sconto" name="sconto" maxlength="5" onblur="verificaValiditaCoupon('<%=utente.getId()%>', this.value)">
                <br>
              </fieldset>

              <input type="hidden" name="id_utente" value="<%= utente.getId() %>">
              <input type="hidden" name="importo" value="<%= carrello.totaleCarrello()%>">

              <button type="submit" id="confermaOrdine">CONFERMA ORDINE</button>
            </div>

            <div id="imgIndirizzo">
              <img src="./images/mappa.jpg" alt="img" id="img">
            </div>
          </div>
          <div class="container">
            <button type="button" onclick="document.getElementById('ordineForm').style.display='none'" class="cancelbtn">Chiudi</button>
            <button type="reset" class="cancelbtn">Pulisci</button>
          </div>

        </form>
      </div>
      <% } %>

    </body>
</html>
