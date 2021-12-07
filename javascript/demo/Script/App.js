
$("#document").onclick(HTTPreq);

function HTTPreq() {

    const method='GET';
    const URL='192,.168.1.222:8080/api/v1/student';
    const xml = new XMLHttpRequest();
    xml.open(method, URL, true);
    xml.responseType = "json";      //ritorna una stringa formattata in JSON.

    xml.onload = function () {
        if (xml.status === 200)
            console.log("richiesta effettuata con successo!")
        else {
            console.log("la richiesta non è arrivata. (codice errore: " + xml.status + ").")            //errori 401,404, ecc..
        }
    }

    xml.onerror = function () {
        console.log("la richiesta non è andata a buon fine.")           //quando la richiesta non va a buon fine (non riesce a raggiungere il server).
    }
}

