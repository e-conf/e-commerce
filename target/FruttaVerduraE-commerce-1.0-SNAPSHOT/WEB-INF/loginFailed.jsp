<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>ERRORE!</title>
        <link rel="stylesheet" href="./css/index.css" type="text/css">
        <link rel="icon" href ="./images/logo.png" type="image/x-icon">
        <meta http-equiv="Refresh" content="2;url=index.html">

        <style>
            body{
                background-color: red;
                color: white;
            }

            div{
                margin: auto;
                text-align: center;
            }
        </style>

    </head>
    <body>
        <div>
            <h1>OPS, SI È VERIFICATO UN ERRORE DURANTE LA PROCEDURA!</h1>
            <h2> Reindirizzamento alla home page in corso. </h2>
        </div>

        <%
            response.setHeader("Refresh", "10;url=next_page.jsp");
        %>

    </body>
</html>
