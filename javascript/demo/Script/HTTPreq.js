
const x=new XMLHttpRequest();
x.responseText="json";

x.onreadystatechange=function(){
    if (x.status<4){
        console.log("richiesta in corso...(stato: "+x.status+")");
    }
    else{
        let response=JSON.stringify(x.response);
        console.log(response);
    }
}
