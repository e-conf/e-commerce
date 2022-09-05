<%@ page import="Model.Prodotto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.Sconto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="it">
    <head>
        <title>GESTIONE SCONTI</title>
        <link href="./css/gestioneprodotti.css" rel="stylesheet" type="text/css">
        <link rel="icon" href ="./images/logo.png" type="image/x-icon">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body>

        <%@ include file="header.jsp" %>

        <% ArrayList<Sconto> listaSconti = (ArrayList<Sconto>) session.getAttribute("listaSconti"); %>
        <div id="gestioneCouponForm">
            <div class="container">
                <fieldset>
                    <legend> COUPON: </legend>
                    <% if(listaSconti.size() == 0){ %>
                    <span> Nessuno sconto presente nel database. </span>
                    <% } else { %>
                    <% for(int j=0; j<listaSconti.size(); j++){ %>
                    <div id="couponDetails<%=j%>">
                        <i class="fas fa-cut" style="color: red" onclick="deleteSconto('<%=listaSconti.get(j).getCodiceSconto()%>', '<%=j%>')"> </i>
                        <i class='fas fa-search-dollar'>  CODICE:  </i> <span class="infoCoupon"> <%=listaSconti.get(j).getCodiceSconto()%> </span> <br>
                        <i class='fas fa-percentage'> PERCENTUALE: </i> <span class="infoCoupon"> <%=listaSconti.get(j).getPercentualeSconto()%> </span> <br>
                        <i class='fas fa-hourglass-start'> DATA FINE COUPON: </i> <span class="infoCoupon"> <%=listaSconti.get(j).getDataFineSconto()%> </span> </p>
                    </div>
                    <% }
                    } %>
                </fieldset>
            </div>
            <a class="icon" href="javascript:history.go(-1)"> <i class="fa fa-reply"> </i> </a>
        </div>

    </body>
</html>

