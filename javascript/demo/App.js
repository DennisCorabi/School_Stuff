window.onload = generateNum;
$("#form").focus();

let tentativi;
let findNum;
let count=0;


$("#newGame").click(generateNum);

function generateNum() {
    $("#responses").text("Numeri inseriti: ");
    $("#counter").text("Tentativi rimanenti: ")
    findNum=Math.floor((Math.random()*100)+1);
    count=0;
    console.log(findNum);
    tentativi = prompt("Inserisci il numero di tentativi");
}


$("#searchBT").click(find);

function find() {
    let num=$("#form").val();
    if (parseInt(num)===findNum) {
        console.log("hai vinto!");
    }
    else if(num>findNum)
        console.log("numero scorretto, riprova (HINT: il numero da trovare è minore di "+num+")");
    else
        console.log("numero scorretto, riprova (HINT: il numero da trovare è maggiore di "+num+")");

    count++;
    if (count==tentativi){
        end();

    }
    $("#responses").text($("#responses").text()+" "+num+", ");
    $("#counter").text("tentativi rimanenti: "+(tentativi-count));
}

function end() {
    $("#searchBT").disabled = true;
    $("#form").disabled = true;
}


//TODO AGGIUNGERE CONTROLLO CHE SI INSERISCA UN NUMERO E NON UNA STRINGA
//TODO AGGIUNGERE LA POSSIBILITÀ ALL'UTENTE DI INSERIRE QUANTI TENTATIVI VUOLE E IL TETTO MASSIMO PER IL NUMERO DA GENERARE.



