
function HTTPrequest(method, url) {
    var xhr = new XMLHttpRequest();
    xhr.open(method,url,true);
    xhr.responseType = "json";
    xhr.send();

    xhr.onload = function () {
        if (xhr.status==200){
            console.log("done.");
            console.log(xhr.response);
        }
        else
            console.log("error " + xhr.status);
    }
    xhr.onprogress = function () {
        console.log("still processing...");
      }
}