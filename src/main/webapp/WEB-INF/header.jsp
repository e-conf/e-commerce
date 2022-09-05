<%@ page import="Model.Utente" %>
<%@ page import="Model.Carrello" %>
<!DOCTYPE html>
<html lang="it">
    <head>
        <title>CONFrutta&Verdura</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="./css/header.css" rel="stylesheet" type="text/css">
        <link href="./css/formpopup.css" rel="stylesheet" type="text/css">
        <link href="./css/mediaqueriesindex.css" rel="stylesheet" type="text/css">
        <script src="./javascript/popupscript.js" crossorigin="anonymous"></script>
        <script src="./javascript/webapp.js" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
        <script>
           $(document).ready(function(){
                $('#goRight').on('click', function(){
                    $('#slideBox').animate({ 'marginLeft' : '0' });
                    $('.topLayer').animate({ 'marginLeft' : '100%' });
                });
                $('#goLeft').on('click', function(){
                    $('#slideBox').animate({ 'marginLeft' : '50%' });
                    $('.topLayer').animate({ 'marginLeft': '0' });
                });
            });

            $(document).ready(function(){
                $('#input').on('click', function(){
                    $('#menu').fadeToggle();
                });
            });
        </script>


    </head>
    <body on>

        <%
            session = request.getSession();

            if(session != null) {
                Utente utente = (Utente) session.getAttribute("utente");
                Carrello carrello = (Carrello) session.getAttribute("carrello");
        %>
        <nav class="navbar">
            <div class="logo-container">
                <img src="./images/logo.png" alt="fruit&vegetables">
            </div>
            <div class="navbar-container">
                <!-- <input id="input" type="checkbox" name="hamburger"> -->
                <div id="input" class="hamburger-lines">
                    <span id="l1" class="line line1"></span>
                    <span id="l2" class="line line2"></span>
                    <span id="l3" class="line line3"></span>
                </div>

                <ul id="menu" class="menu-items">
                    <li> <a href="ServletHome" class="menuitem"> Home <i class='fas fa-home'> </i> </a> </li>
                    <li> <a href="ServletAboutRedirect" class="menuitem"> Chi siamo <i class='far fa-newspaper'> </i> </a> </li>
                    <li> <a href="ServletProdotti" class="menuitem"> Prodotti <i class='fas fa-leaf'> </i> </a> </li>
                    <% if((utente == null) || (utente != null && !utente.isAdmin())){ %>
                    <li> <a href="ServletCarrelloRedirect" class="menuitem" class="notification"> Carrello <i class='fas fa-shopping-cart'> </i> <span class="badge" id="badge">
                        <% if(carrello != null){ %>
                            <% if(carrello.getListaProdotti().size() > 0 ){ %>
                                <%= carrello.getListaProdotti().size()%>
                            <% } %>
                        <% } %>
                            </span>
                        </a>
                    </li>
                    <% }

                    if(utente != null){ %>
                    <li> <a href="ServletAccount" class="menuitem"> <%= utente.getNome() %> <%= utente.getCognome() %> <i class='fas fa-user-check'> </i> </a> </li>
                    <li> <a href="ServletLogout" class="menuitem"> Logout <i class='fas fa-power-off'> </i> </a> </li>
                </ul>
                    <% } else { %>
                    <li> <a href="#" onclick="apriForm()" style="width:auto;" class="menuitem">Accedi <i class='fas fa-user-alt'> </i> </a> </li>
                </ul>
                    <% } %>
            </div>
        </nav>
        <% } %>

        <div id="form" class="popup">
            <span style="background-color: white" onclick="document.getElementById('form').style.display='none'" class="close" title="Close Modal">&times;</span>
            <div id="back">
                <div class="backRight"></div>
                <div class="backLeft"></div>
            </div>

            <div id="slideBox">
                <div class="topLayer">
                    <div class="left">
                        <div class="content">
                            <h2> REGISTRATI </h2>
                            <form method="POST" name="formRegistrazione" action="ServletSignup" onsubmit="return validateForm();">
                                <div class="form-group">
                                    <input type="text" placeholder="Inserisci il nome"  id="name" name="name" required>
                                    <input type="text" placeholder="Inserisci il cognome" id="surname" name="surname" required>
                                    <input type="text" placeholder="Inserisci email" id="email" name="email" required onblur="controlloEmailDatabase(this.value)">
                                    <input type="password" placeholder="Inserisci password" id="password" name="password" required>
                                </div>
                                <button type="button" id="goLeft" class="off"> LOGIN </button>
                                <button type="submit" id="registrati" style="background-color: #0288D1;"> REGISTRATI </button>
                            </form>
                        </div>
                    </div>
                    <div class="right">
                        <span style="background-color: white" onclick="document.getElementById('form').style.display='none'" class="close" title="Close Modal">&times;</span>

                        <div class="content">
                            <h2 style="color: white"> LOGIN </h2>
                            <form method="POST" name="formLogin" action="ServletLogin" onsubmit="return validateFormLogin();">
                                <div class="form-group">
                                    <input type="text" id="emailInput" name="e-mail" placeholder="Inserisci email" onblur="controlloEmailDatabase(this.value)">
                                    <input type="password" id="passwordInput" name="psw" placeholder="Inserisci password">
                                </div>
                                <button type="button" id="goRight" class="off"> REGISTRATI </button>
                                <button id="login" type="submit" style="background-color: #04AA6D;"> LOGIN </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- LOGIN/SIGNUP FORM - MOBILE -->
        <div id="formMobileRegistrazione" class="popup">
            <span style="background-color: white" onclick="document.getElementById('formMobileRegistrazione').style.display='none'" class="close" title="Close Modal">&times;</span>
            <form name="formMobileRegistrazione" action="ServletSignup" method="post" onsubmit="return validateFormRegistrazioneMobile();">
                <h1> REGISTRAZIONE </h1>
                <div class="form-input">
                    <input type="text" placeholder="Inserisci il nome"  name="name" required>
                    <input type="text" placeholder="Inserisci il cognome" name="surname" required>
                    <input type="text" placeholder="Inserisci email" name="email" id="emailRegistrazioneMobileForm"required onblur="controlloEmailDatabase(this.value)">
                    <input type="password" placeholder="Inserisci password" id="passwordRegistrazioneMobileForm" name="password" required>
                </div>
                <button type="button" class="off" onclick="gestisciForm()"> LOGIN </button>
                <button type="submit" id="signupButton" idstyle="background-color: #0288D1;"> REGISTRATI </button>
            </form>
        </div>

        <div id="formMobileLogin" class="popup">
            <span style="background-color: white" onclick="document.getElementById('formMobileLogin').style.display='none'" class="close" title="Close Modal">&times;</span>
            <form name="formMobileLogin" action="ServletLogin" method="post" onsubmit="return validateFormLoginMobile();">
                <h1> LOGIN </h1>
                <div class="form-input">
                    <input type="text" name="e-mail" id="emailLoginMobileForm" placeholder="Inserisci email" onblur="controlloEmailDatabase(this.value)">
                    <input type="password" name="psw" id="passwordLoginMobileForm" placeholder="Inserisci password">
                </div>
                <button type="button" class="off" onclick="gestisciForm()"> REGISTRATI </button>
                <button type="submit" id="loginButton"> LOGIN </button>
            </form>
        </div>

    </body>
</html>