<%@ page import="Model.Utente" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.Prodotto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<html lang="it">
    <head>
        <title>CONFrutta&Verdura</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="./css/index.css" rel="stylesheet" type="text/css">
        <link rel="icon" href ="./images/logo.png" type="image/x-icon">
    </head>
    <body>

        <%@ include file="header.jsp" %>

        <div id="header">
            <div id="sfondo">
                <img src="./images/sfondo.jpg" alt="sfondo">
                <div id="text">
                    CONFrutta & Verdura
                </div>
            </div>
            <div class="titolo">
               <p id="descrizione"> Compra frutta e verdura comodamente da casa.</p>
            </div>

            <div class="media">
                <div class="citazione">
                    <p class="quote"> <i> "L’agricoltura indica cos’è più e cos’è meno necessario. Essa guida razionalmente la vita. Bisogna toccare la terra." </i> </p>
                    <p class="author"> Lev Tolstoj </p>
                </div>
                <div class="bannerContainer">
                    <a href="ServletProdotti">
                        <img class="banner" src="./images/banner1.png" alt="banner">
                        <img class="banner" src="./images/banner2.png" alt="banner">
                        <img class="banner" src="./images/banner4.png" alt="banner">
                    </a>
                </div>
            </div>

            <div class="media">
                <div class="video">
                    <video width="640" height="480" autoplay loop muted>
                        <source src="./images/videoBio.mp4" type="video/mp4">
                        <source src="./images/videoBio.mp4" type="video/ogg">
                        Your browser does not support the video tag.
                    </video>
                </div>
                <div class="bio">
                    <p> Perché Bio? </p>
                    <p>
                        Scegliere prodotti biologici permette di dare attenzione alla salute,
                        rispettare il proprio territorio e guadagnarci anche in termini di sapore e qualità.
                    </p>
                    <p> È anche e soprattutto una scelta etica. </p>
                    <p>
                        Scegliere ‘bio’ è anche un modo per amare noi stessi e l’ambiente, perché non comporta spreco di materie prime,
                        rispetta le biodiversità e ha un minimo impatto ambientale.
                    </p>
                </div>
            </div>
        </div>

        <%@ include file="footer.jsp" %>

        <script>
            var myIndex = 0;
            carouselBanner();

            function carouselBanner() {
                let i;
                let x = document.getElementsByClassName("banner");
                for (i = 0; i < x.length; i++) {
                    x[i].style.display = "none";
                }
                myIndex++;
                if (myIndex > x.length) {
                    myIndex = 1
                }
                x[myIndex - 1].style.display = "block";
                setTimeout(carouselBanner, 5000);
            }
        </script>

    </body>
</html>





