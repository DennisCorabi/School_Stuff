window.onload=function(){
    let title;
    localStorage.setItem("name",prompt("inserisci il tuo nome"));
    const name=localStorage.getItem("name");

    title=document.getElementById("title");
    title.innerText=title.innerText+" (hello "+name+",too!)";
}



//AMONG US IMAGE SECTION
const image=document.getElementById("amongus");
let imageclicks=0;

image.onmouseleave=changeImg;
image.onmouseover=changeImg;


function changeImg(){
    let imgSource=image.getAttribute("src");
    if (imgSource==="https://loghi-famosi.com/wp-content/uploads/2021/08/Among-Us-Logo.png"){
        image.setAttribute('src',"https://c.tenor.com/jUMex_rdqPwAAAAM/among-us-twerk.gif");
    }
    else{
        image.setAttribute("src","https://loghi-famosi.com/wp-content/uploads/2021/08/Among-Us-Logo.png");
    }
}

image.onclick=function(){
    imageclicks++;
    if (imageclicks===localStorage.getItem("name").length)
    {     let amongus=document.getElementById("amongus-describe");

        image.setAttribute("src","https://i.kym-cdn.com/photos/images/newsfeed/002/111/324/976.gif");
        amongus.innerHTML=amongus.innerHTML+"<h1>Easter egg!!</h1>";
    }
}

//AMONG US TITLE SECTION
text=document.getElementById("amongus-text");
text.onclick=function(){
    const lista=["amongus","sumoga","amonga","coronga","A","Sugoma","Amogus","Amoamogus"];
    lista.splice(lista.indexOf(document.getElementById("amongus-text")));
}

