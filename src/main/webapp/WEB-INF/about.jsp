<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>CHI SIAMO</title>
        <link href="./css/index.css" rel="stylesheet" type="text/css">
        <link rel="icon" href ="./images/logo.png" type="image/x-icon">
    </head>
    <body>

        <%@ include file="header.jsp" %>

        <div id="bodypage">

            <div id="gallerysection">
                <div class="titlesection">
                    <h2>Gallery</h2>
                </div>
                <div class="subtitlesection">
                    <h3>Verdura</h3>
                </div>

                <div class="gallerycontainer">
                    <img class="vegetables" src="./images/gallery/verdura/campo_cipolle.jpg" alt="campo cipolle">
                    <img class="vegetables" src="./images/gallery/verdura/fagiolinipianta.jpg" alt="fagiolini piante">
                    <img class="vegetables" src="./images/gallery/verdura/finocchipiante.jpg" alt="finocchi piante">
                    <img class="vegetables" src="./images/gallery/verdura/pianta.jpeg" alt="pianta">
                    <img class="vegetables" src="./images/gallery/verdura/semifagioli.jpg" alt="fagiolini semi">
                    <img class="vegetables" src="./images/gallery/verdura/finocchi_polistirolo.jpg" alt="finocchi polistirolo">
                    <img class="vegetables" src="./images/gallery/verdura/campo_lattuga.jpeg" alt="campo lattuga">
                    <img class="vegetables" src="./images/gallery/verdura/campoverdure.png" alt="campo verdure">
                    <img class="vegetables" src="./images/gallery/verdura/cipolle.jpg" alt="cipolle">
                    <img class="vegetables" src="./images/gallery/verdura/fagiolinicoltivazione.jpg" alt="fagiolini coltivazione">
                    <img class="vegetables" src="./images/gallery/verdura/finocchi.jpg" alt="finocchi">
                    <img class="vegetables" src="./images/gallery/verdura/friarielli.jpg" alt="friarielli">
                    <img class="vegetables" src="./images/gallery/verdura/lattugafocus.jpg" alt="lattuga">
                    <img class="vegetables" src="./images/gallery/verdura/melanzane.jpg" alt="melanzane">
                    <img class="vegetables" src="./images/gallery/verdura/pomodorino.png" alt="pomodorino">
                    <img class="vegetables" src="./images/gallery/verdura/verdure.jpg" alt="verdure">
                </div>

                <div class="subtitlesection">
                    <h3>Frutta</h3>
                </div>

                <div class="gallerycontainer">
                    <img class="fruit" src="./images/gallery/frutta/albicocca.jpg" alt="albicocca">
                    <img class="fruit" src="./images/gallery/frutta/arance.jpg" alt="arance">
                    <img class="fruit" src="./images/gallery/frutta/meloni.jpg" alt="meloni">
                    <img class="fruit" src="./images/gallery/frutta/angurie.jpg" alt="angurie">
                    <img class="fruit" src="./images/gallery/frutta/ciliegie.jpg" alt="ciliegie">
                    <img class="fruit" src="./images/gallery/frutta/fragole.jpg" alt="fragole">
                    <img class="fruit" src="./images/gallery/frutta/mele.png" alt="mele">
                    <img class="fruit" src="./images/gallery/frutta/melograno.jpg" alt="melograno">
                    <img class="fruit" src="./images/gallery/frutta/nespole.jpg" alt="nespole">
                    <img class="fruit" src="./images/gallery/frutta/pesca.jpg" alt="pesca">
                    <img class="fruit" src="./images/gallery/frutta/prugne.jpg" alt="prugna">
                    <img class="fruit" src="./images/gallery/frutta/uva.jpg" alt="uva">
                </div>
            </div>

            <div id="main">
                <h2 id="descriptiontitle"> Chi siamo </h2>

                <p> CONFrutta & Verdura è un e-commerce che consente ai clienti di acquistare frutta e verdura a domicilio, in modo semplice e soprattutto veloce. </p>

                <p>
                    CONFrutta & Verdura è rifornita da una serie di aziende locali, che coltivano frutta e verdura in modo naturale, senza pesticidi.
                    La cura del prodotto è un aspetto fondamentale che consente di avere a disposizione prodotti di elevatà qualità.
                </p>
                <p>
                    Scegliere CONFrutta & Verdura è semplice per tre motivi:
                <ul>
                    <li> Prodotti freschi: tutta la frutta e la verdura presente sul sito è raccolta al massimo due giorni prima della vendita, in modo tale
                        da garantire al cliente un prodotto fresco;
                    </li>
                    <li> Prodotti stagionali: ciascun prodotto è di stagione e possiede proprietà nutrizionali opportune alla stagione corrente; </li>
                    <li> Consegne veloci e affidabili: effettuate da personale specializzato in modo da evitare danni ai prodotti; <br>
                        <img src="./images/corriere.png" alt="corriere" id="corriere">
                    </li>
                </ul>
                </p>
            </div>
        </div>

        <%@ include file="footer.jsp" %>

        <script>
            var myIndex1 = 0;
            carouselVegetables();

            function carouselVegetables() {
                let i;
                let x = document.getElementsByClassName("vegetables");
                for (i = 0; i < x.length; i++) {
                    x[i].style.display = "none";
                }
                myIndex1++;
                if (myIndex1 > x.length) {
                    myIndex1 = 1
                }
                x[myIndex1 - 1].style.display = "block";
                setTimeout(carouselVegetables, 2000);
            }

            var myIndex2 = 0;
            carouselFruit();

            function carouselFruit() {
                let i2;
                let x2 = document.getElementsByClassName("fruit");
                for (i2 = 0; i2 < x2.length; i2++) {
                    x2[i2].style.display = "none";
                }
                myIndex2++;
                if (myIndex2 > x2.length) {
                    myIndex2 = 1
                }
                x2[myIndex2 - 1].style.display = "block";
                setTimeout(carouselFruit, 2000);
            }
        </script>
    </body>
</html>
