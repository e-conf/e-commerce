<%@ page import="com.sun.net.httpserver.HttpPrincipal" %>
<%@ page import="Model.Prodotto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>UPDATE</title>
        <link href="./css/modificaSuccesso.css" rel="stylesheet" type="text/css">
        <link rel="icon" href ="./images/logo.png" type="image/x-icon">
    </head>
    <body>

        <%@ include file="header.jsp"%>

        <% String result = (String) session.getAttribute("modificaProdotto");
           if(result.equalsIgnoreCase("ok")){
               Prodotto prodotto = (Prodotto) session.getAttribute("prodottoModificato");
        %>
        <div id="modificaOk">
            <h1> MODIFICA AVVENUTA CON SUCCESSO!<i class="fa fa-check-circle" style="color:green"></i></h1>
            <table>
                <tr>
                    <th> CODICE: </th>
                    <td> <%=prodotto.getId_prodotto()%> </td>
                </tr>
                <tr>
                    <th> NOME:</th>
                    <td> <%=prodotto.getNome()%> </td>
                </tr>
                <tr>
                    <th> PROVENIENZA: </th>
                    <td> <%=prodotto.getProvenienza()%> </td>
                </tr>
                <tr>
                    <th> DESCRIZIONE: </th>
                    <td> <%=prodotto.getDescrizione()%> </td>
                </tr>
                <tr>
                    <th> QUANTITA: </th>
                    <td> <%=prodotto.getQuantita()%> </td>
                </tr>
                <tr>
                    <th> PREZZO: </th>
                    <td> <%=prodotto.getPrezzo()%> </td>
                </tr>
                <tr>
                    <th> CATEGORIA: </th>
                    <td> <%=prodotto.getCategoria()%> </td>
                </tr>
                <tr>
                    <th> FORMATO: </th>
                    <td> <%=prodotto.getFormato()%> </td>
                </tr>
            </table>
            <a class="icon" href="javascript:history.go(-1)"> <i class="fa fa-reply"> </i> </a>
            <a class="icon" href="ServletHome"> <i class="fa fa-home"> </i> </a>
        </div>
        <% } else { %>
        <div id="modificaOk">
            <h1 style="color: red"> SI È VERIFICATO UN ERRORE DURANTE LA MODIFICA. <i class="fa fa-exclamation-triangle" style="color:red"> </i> </h1>
            <a class="icon" href="javascript:history.go(-1)" style="color: blue;"> Torna alla pagina precedente <i class="fa fa-reply"> </i> </a> <br>
            <a class="icon" href="ServletHome" style="color: blue;"> Torna alla home-page<i class="fa fa-home"> </i> </a>
        </div>
        <% } %>
    </body>
</html>
