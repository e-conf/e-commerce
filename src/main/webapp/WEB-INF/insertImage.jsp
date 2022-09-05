<%@ page import="Model.Prodotto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="it">
    <head>
        <title>UPDATE IMAGE</title>
        <link href="./css/gestioneprodotti.css" rel="stylesheet" type="text/css">
        <link rel="icon" href ="./images/logo.png" type="image/x-icon">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body>

        <%@ include file="header.jsp" %>

        <% String id_prodotto = (String) session.getAttribute("idImmagineProdotto"); %>
        <% String immagine = (String) session.getAttribute("immagine"); %>

        <div id="upload">
            <form method="post" action="insert" name="updateImageForm" enctype="multipart/form-data">
                <fieldset>
                    <legend>Select file-image</legend>
                    <input type="hidden" name="id_prodotto" value="<%=id_prodotto%>">
                    <input type="hidden" name="immagine" value="<%=immagine%>">
                    <input type="file" name="file" size="50" required accept="image/*"/><br>
                    <input type="submit" value="Invia">
                    <input type="reset" value="Reset">
                </fieldset>
            </form>
            <a class="icon" href="javascript:history.go(-1)"> <i class="fa fa-reply"> </i> </a>
        </div>

    </body>
</html>


