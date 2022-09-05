//HEADER.JSP
//Funzione che gestisce quale form di login/signup aprire in base alla dimensione dello schermo.
function apriForm(){
    const pageWidth  = document.documentElement.scrollWidth;
    if(pageWidth <= 600){
        document.getElementById('form').style.display = 'none';
        document.getElementById('formMobileLogin').style.display = 'block';
    }else {
        document.getElementById('form').style.display = 'block';
    }
}

//Funzione per la gestione dell'apertura e della chiusura dei form di login/signup versione mobile.
function gestisciForm(){
    if(document.getElementById('formMobileRegistrazione').style.display == 'block'){
        document.getElementById('formMobileLogin').style.display = 'block';
        document.getElementById('formMobileRegistrazione').style.display = 'none';
    } else {
        document.getElementById('formMobileRegistrazione').style.display = 'block';
        document.getElementById('formMobileLogin').style.display = 'none';
    }
}

//Funzione che verifica la disponibilità dell'email inserita nel database.
function controlloEmailDatabase(email){
    const xmlhttp = new XMLHttpRequest();

    xmlhttp.onreadystatechange = function (){
        if(this.readyState == 4 && this.status == 200){
            verificaDisponibilitaEmail(this);
        }
    }

    xmlhttp.open("GET", "ServletVerificaEmailDatabase?email="+email, true);
    xmlhttp.send();
}

function verificaDisponibilitaEmail(xmlhttp){
    let ogg = JSON.parse(xmlhttp.responseText);
    let flag = ogg[0].flag;
    if(flag == "false"){
        document.getElementById("registrati").disabled = true;
        document.getElementById("registrati").style.backgroundColor = "red";
        document.getElementById("login").disabled = false;
        document.getElementById("login").style.backgroundColor = "#04AA6D";

        document.getElementById("signupButton").disabled = true;
        document.getElementById("signupButton").style.backgroundColor = "red";
        document.getElementById("loginButton").disabled = false;
        document.getElementById("loginButton").style.backgroundColor = "#04AA6D";
    }else{
        document.getElementById("registrati").disabled = false;
        document.getElementById("registrati").style.backgroundColor = "#03A9F4";
        document.getElementById("login").disabled = true;
        document.getElementById("login").style.backgroundColor = "red";

        document.getElementById("signupButton").disabled = false;
        document.getElementById("signupButton").style.backgroundColor = "#03A9F4";
        document.getElementById("loginButton").disabled = true;
        document.getElementById("loginButton").style.backgroundColor = "red";
    }
}

//Funzione che controlla la correttezza sintattica della password.
function verificaPassword(password){
    if(password.length < 8)
        return false;

    const maiuscolaRGEX = /[A-Z]/;
    if(!maiuscolaRGEX.test(password))
        return false;

    const minuscolaRGEX = /[a-z]/;
    if(!minuscolaRGEX.test(password))
        return false;

    const numeroRGEX = /[0-9]/;
    if(!numeroRGEX.test(password))
        return false;

    const specialCharRGEX = /[@#$%^&+=?.!]/;
    if(!specialCharRGEX.test(password))
        return false;

    return true;
}

//Funzione che verifica la correttezza sintattica dell'email inserita.
function verificaEmail(email){
    var emailRGEX = /^[A-Z0-9._%+-]+@([A-Z0-9-]+\.)+[A-Z]{2,4}$/i;
    if(emailRGEX.test(email))
        return true;
    else
        return false;
}

//Funzione per la validazione del form di registrazione (versione desktop).
function validateForm() {
    const pageWidth  = document.documentElement.scrollWidth;
    let email = document.getElementById("email").value;
    let password = document.getElementById("password").value;

    let verificaMail = verificaEmail(email);
    let verificaPass = verificaPassword(password);

    if((!verificaPass)&&(verificaMail)){
        alert("La password inserita non rispetta il seguente formato: almeno 8 caratteri, almeno un carattere speciale, almeno un numero, almeno una lettera maiuscola e minuscola");
        return false;
    }else if((verificaPass)&&(!verificaMail)){
        alert("Email inserita non valida.");
        return false;
    }else if((!verificaPass)&&(!verificaMail)){
        alert("Email e password non rispettano il giusto formato.")
        return false;
    }
    else if((verificaPass)&&(verificaMail)) {
        document.formRegistrazione.submit();
        return true;
    }
}

//Funzione per la validazione del form di registrazione (versione mobile).
function validateFormRegistrazioneMobile() {
    const pageWidth  = document.documentElement.scrollWidth;
    let email = document.getElementById("emailRegistrazioneMobileForm").value;
    let password = document.getElementById("passwordRegistrazioneMobileForm").value;

    let verificaMail = verificaEmail(email);
    let verificaPass = verificaPassword(password);

    if((!verificaPass)&&(verificaMail)){
        alert("La password inserita non rispetta il seguente formato: almeno 8 caratteri, almeno un carattere speciale, almeno un numero, almeno una lettera maiuscola e minuscola");
        return false;
    }else if((verificaPass)&&(!verificaMail)){
        alert("Email inserita non valida.");
        return false;
    }else if((!verificaPass)&&(!verificaMail)){
        alert("Email e password non rispettano il giusto formato.")
        return false;
    }
    else if((verificaPass)&&(verificaMail)){
        document.formMobileRegistrazione.submit();
        return true;
    }
}

//Funzione per la validazione del form di login (versione desktop).
function validateFormLogin() {
    const pageWidth  = document.documentElement.scrollWidth;
    let email = document.getElementById("emailInput").value;
    let password = document.getElementById("passwordInput").value;

    let verificaMail = verificaEmail(email);
    let verificaPass = verificaPassword(password);

    if((!verificaPass)&&(verificaMail)){
        alert("La password inserita non rispetta il seguente formato: almeno 8 caratteri, almeno un carattere speciale, almeno un numero, almeno una lettera maiuscola e una minuscola");
        return false;
    }else if((verificaPass)&&(!verificaMail)){
        alert("Email inserita non valida.");
        return false;
    }else if((!verificaPass)&&(!verificaMail)){
        alert("Email e la password non rispettano il giusto formato.")
        return false;
    }
    else if((verificaPass)&&(verificaMail)){
        document.formLogin.submit();
        return true;
    }
}

//Funzione per la validazione del form di login (versione mobile).
function validateFormLoginMobile() {
    const pageWidth  = document.documentElement.scrollWidth;
    let email = document.getElementById("emailLoginMobileForm").value;
    let password = document.getElementById("passwordLoginMobileForm").value;

    let verificaMail = verificaEmail(email);
    let verificaPass = verificaPassword(password);

    if((!verificaPass)&&(verificaMail)){
        alert("La password inserita non rispetta il seguente formato: almeno 8 caratteri, almeno un carattere speciale, almeno un numero, almeno una lettera maiuscola e una minuscola");
        return false;
    }else if((verificaPass)&&(!verificaMail)){
        alert("Email inserita non valida.");
        return false;
    }else if((!verificaPass)&&(!verificaMail)){
        alert("Email e la password non rispettano il giusto formato.")
        return false;
    }
    else if((verificaPass)&&(verificaMail)){
        document.formMobileLogin.submit();
        return true;
    }
}

//PRODOTTI.JSP
//Funzione che inserisce un prodotto nel carrello e segnala il numero di prodotti inseriti in esso.
function carrelloFunction(id, quantita){
    const xmlhttp = new XMLHttpRequest();

    xmlhttp.onreadystatechange = function (){
        if(this.readyState == 4 && this.status == 200){
            changeNotification(this);
        }
    }

    xmlhttp.open("GET", "ServletCarrello?id_prodotto="+id+"&quantita="+quantita, true);
    xmlhttp.send();
}

function changeNotification(xmlhttp){
    let ogg = JSON.parse(xmlhttp.responseText);
    let size = ogg[0].numeroElementi;
    document.getElementById("badge").innerHTML = size;
}

//Funzione che filtra i prodotti per categoria.
function categoria_function(categoria){
    const xmlhttp = new XMLHttpRequest();

    xmlhttp.onreadystatechange = function (){
        if(this.readyState == 4 && this.status == 200){
            myFunction(this);
        }
    }

    xmlhttp.open("GET", "ServletCategoria?categoria="+categoria, true);
    xmlhttp.send();
}

function myFunction(xmlhttp){
    let i;
    let data = JSON.parse(xmlhttp.responseText);
    let divImage = "";

    for(i = 0; i < data.length ; i++){
        let functionAttribute = "onclick=\"carrelloFunction('" + data[i].id + "','1')\"";

        if (data[i].quantita > 0) {
            divImage += "<div class='gallerysection'>" +
                "<div class='gallerycontainer'>" +
                "<img src='./images/shop/" + data[i].immagine + "'>" +
                "</div>" +
                "<div>" +
                "<p class='productname'>" +
                data[i].nome +
                "</p>" +
                "<p class='price'> " + data[i].prezzo + "€ - " + data[i].formato + "</p>" +
                "<p class='productorigin'>" +
                data[i].provenienza +
                "</p>" +
                "<button " + functionAttribute+"> AGGIUNGI AL CARRELLO </button>" +
                "</div>" +
                "</div>";
        } else {
            divImage += "<div class='gallerysection'>" +
                "<div class='gallerycontainer'>" +
                "<img src='./images/shop/" + data[i].immagine + "'>" +
                "</div>" +
                "<div>" +
                "<p class='productname'>" +
                data[i].nome +
                "</p>" +
                "<p class='price'> " + data[i].prezzo + "€ - " + data[i].formato + "</p>" +
                "<p class='productorigin'>" +
                data[i].provenienza +
                "</p>" +
                "<button style='background-color: red'> PRODOTTO NON DISPONIBILE </button>" +
                "</div>" +
                "</div>";
        }
    }

    document.getElementById("productcontainer").innerHTML = divImage;
}


let element = document.getElementById('insertproduct');
window.onclick = function(event) {
    if (event.target == element) {
        element.style.display = "none";
    }
}

//ACCOUNT.JPS
//Funzione che mostra i dettagli di un ordine.
function showDetails(id, i) {
    const xmlhttp = new XMLHttpRequest();

    xmlhttp.onreadystatechange = function (){
        if(this.readyState == 4 && this.status == 200){
            doRetrieveDetails(this, i);
        }
    }

    xmlhttp.open("GET", "ServletDettaglioOrdine?id_ordine="+id, true);
    xmlhttp.send();
}

function doRetrieveDetails(xmlhttp, i){
    let ogg = JSON.parse(xmlhttp.responseText);
    let idContainer = "details"+i;
    let infoIdContainer = "moreInfo"+i;
    let id = ogg[0].id_ordine;
    let data = ogg[0].data_ordine;
    let importo = ogg[0].importo;
    let functionAttribute = "onclick=\"showMoreInfo('" + id + "','" + i + "')\"";

    if(document.getElementById(idContainer).style.display == "block"){
        document.getElementById(idContainer).style.display = "none";
        document.getElementById(infoIdContainer).style.display = "none";
    } else {
        document.getElementById(idContainer).style.display = "block";
        document.getElementById(idContainer).innerHTML = "<label class='infoOrdine'> ID ORDINE: </label> <p class='info'>" + id + "</p> <label class='infoOrdine'> DATA: </label> <p class='info'>" + data + "</p> <label class='infoOrdine'> IMPORTO: </label> <p class='info'>&#8364;" + importo + "</p> <i class='fas fa-info-circle' "+functionAttribute+"> </i>";
    }
}

function showMoreInfo(id, i){
    const xmlhttp = new XMLHttpRequest();

    xmlhttp.onreadystatechange = function (){
        if(this.readyState == 4 && this.status == 200){
            showMoreInfoOrder(this, i);
        }
    }

    xmlhttp.open("GET", "ServletDettaglioProdottiOrdine?id_ordine="+id, true);
    xmlhttp.send();
}

function showMoreInfoOrder(xmlhttp, i) {
    let ogg = JSON.parse(xmlhttp.responseText);
    let idContainer = "moreInfo" + i;

    if(document.getElementById(idContainer).style.display == "block"){
        document.getElementById(idContainer).style.display = "none";
    } else {
        document.getElementById(idContainer).style.display = "block";
        let obj = "";
        for (let i = 0; i < ogg.length; i++) {
            obj += "<label class='infoOrdine'> ID PRODOTTO: </label> <p class='info'>" + ogg[i].id_prodotto + "</p> <label class='infoOrdine'> PREZZO: </label> <p class='info'>&#8364;" + ogg[i].prezzo + "</p> <label class='infoOrdine'> QUANTITA: </label> <p class='info'>" + ogg[i].quantitaOrdine + "</p> <label class='infoOrdine'> TOTALE PRODOTTO: </label> <p class='info'>&#8364;" + ogg[i].totale + "<hr> </p> ";
        }
        document.getElementById(idContainer).innerHTML = obj;
    }

}

//Funzione che verifica se la nuova password rispetta la sintassi definita.
function verificaCambioPassword(){
    if(verificaPassword()){
        return true;
    } else {
        alert("Si è verificato un errore. Riprova.");
        return false;
    }
}

//CARRELLO.JSP
//Aumenta la quantità di un prodotto già presente nel carrello.
function increaseQuantity(id, prezzo, quantita, num){
    const xmlhttp = new XMLHttpRequest();

    xmlhttp.onreadystatechange = function (){
        if(this.readyState == 4 && this.status == 200){
            changeQuantity(prezzo, num, quantita, xmlhttp);
        }
    }

    xmlhttp.open("GET", "ServletQuantitaCarrello?prodotto="+id+"&quantita="+quantita, true);
    xmlhttp.send();
}

function changeQuantity(prezzo, num, quantita, xmlhttp) {
    let idName = "totale" + num;
    let x = (prezzo * quantita).toFixed(2);
    document.getElementById(idName).innerHTML = "TOTALE: &#8364;" + x;

    let ogg = JSON.parse(xmlhttp.responseText);
    let totaleCarrello = ogg[0].totaleCarrello;
    document.getElementById("totaleSpesa").innerHTML = "TOTALE: &#8364;"+totaleCarrello;
}

//Funzione che elimina un prodotto dal carrello, segnalandone l'avvenuta rimozione.
function deleteProductCart(id, index){
    let domanda = confirm("Eliminare il prodotto dal carrello?");
    if(domanda) {
        const xmlhttp = new XMLHttpRequest();

        xmlhttp.onreadystatechange = function (){
            if(this.readyState == 4 && this.status == 200){
                replaceProdotto(this, index);
            }
        }

        xmlhttp.open("GET", "ServletDeleteProductCart?codice="+id, true);
        xmlhttp.send();
    }
}

function replaceProdotto(xmlhttp, i) {
    let ogg = JSON.parse(xmlhttp.responseText);
    let variabile = "productBox"+i;
    document.getElementById(variabile).innerHTML = "Il prodotto è stato eliminato.";
    document.getElementById('totaleSpesa').innerHTML = "TOTALE: "+ogg[0].totaleCarrello+"&#8364;";
}

//Funzione che verifica se il codice sconto è valido, sintatticamente e semanticamente (dal punto di vista della logica della web-app).
function verificaValiditaCoupon(id_utente, codice_sconto){
        let xmlhttp = new XMLHttpRequest();

        xmlhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                stampaValiditaCoupon(xmlhttp);
            }
        }

        xmlhttp.open("GET", "ServletVerificaValiditaCoupon?id_utente=" + id_utente +"&codice_sconto=" + codice_sconto, true);
        xmlhttp.send();
}

function stampaValiditaCoupon(xmlhttp){
    let ogg = JSON.parse(xmlhttp.responseText);

    if(ogg[0].validita == "true"){
        document.getElementById('confermaOrdine').disabled = false;
        document.getElementById('confermaOrdine').style.backgroundColor = "#04AA6D";
        document.getElementById('confermaOrdine').style.opacity = "1";
        alert("Coupon valido. Percentuale di sconto: " + ogg[0].percentuale + "%");
    } else if(ogg[0].validita == "false"){
        document.getElementById('confermaOrdine').disabled = true;
        document.getElementById('confermaOrdine').style.backgroundColor = "red";
        alert("Coupon errato.");
    } else if(ogg[0].validita == "null"){
        document.getElementById('confermaOrdine').disabled = false;
        document.getElementById('confermaOrdine').style.backgroundColor = "#04AA6D";
        document.getElementById('confermaOrdine').style.opacity = "1";
    } else if(ogg[0].validita == "justUsed"){
        document.getElementById('confermaOrdine').disabled = true;
        document.getElementById('confermaOrdine').style.backgroundColor = "red";
        alert("Coupon già utilizzato.");
    } else if(ogg[0].validita == "scaduto"){
        document.getElementById('confermaOrdine').disabled = true;
        document.getElementById('confermaOrdine').style.backgroundColor = "red";
        alert("Coupon scaduto in data: " + ogg[0].data);
    }
}

//RIEPILOGOORDINE.JPS
//Funzione che consente la stampa del contenuto di un elemento.
function stampa() {
    let nw = window.open();
    nw.document.write(document.getElementById("riepilogoOrdineContainer").innerHTML);
    nw.print();
    nw.close();
}

//GESTIONEPRODOTTI.JSP
//Funzione che consente di far comparire il popup in cui l'amministratore inserisce i dati del nuovo sconto.
function inserisciDatiCoupon(){
    document.getElementById('couponForm').style.display = "block";
}

//Funzione che verifica se il codice è già presente nel database.
function controlloCodiceDatabase(codice){
    const xmlhttp = new XMLHttpRequest();

    xmlhttp.onreadystatechange = function (){
        if(this.readyState == 4 && this.status == 200){
            verificaDisponibilitaCodiceSconto(this);
        }
    }

    xmlhttp.open("GET", "ServletVerificaValiditaCodiceSconto?codice="+codice, true);
    xmlhttp.send();
}

function verificaDisponibilitaCodiceSconto(xmlhttp){
    let ogg = JSON.parse(xmlhttp.responseText);
    let flag = ogg[0].flag;

    if(flag == "false"){
        document.getElementById("inserisciCoupon").disabled = true;
        document.getElementById("inserisciCoupon").style.backgroundColor = "red";
    }else{
        document.getElementById("inserisciCoupon").disabled = false;
        document.getElementById("inserisciCoupon").style.backgroundColor = "#04AA6D";
    }
}

//Funzione che elimina uno sconto dal database e ne segnala l'avvenuta rimozione.
function deleteSconto(codice, indice){
    const xmlhttp = new XMLHttpRequest();

    xmlhttp.onreadystatechange = function (){
        if(this.readyState == 4 && this.status == 200){
            replaceSconto(this, indice);
        }
    }

    xmlhttp.open("GET", "ServletDeleteScontoDatabase?codice="+codice, true);
    xmlhttp.send();
}

function replaceSconto(xmlhttp, j){
    let contenitore = "couponDetails"+j;
    document.getElementById(contenitore).innerHTML = "Il coupon è stato eliminato. <br>";
}

//Funzione che verifica se il nuovo codice sconto inserito dall'amministratore rispetta la sintassi definita.
function validateCodiceSconto(){
    let codiceSconto = document.getElementById('codice').value;
    if(codiceSconto.length == 5){
        return true;
    } else {
        alert("Il codice deve essere composto da 5 caratteri");
        return false;
    }
}