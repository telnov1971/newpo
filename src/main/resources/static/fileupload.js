function refresh(id){
    var XMLHttpRequestObject = false;
    if (window.XMLHttpRequest)
        XMLHttpRequestObject = new XMLHttpRequest();
    else if(window.ActiveXobject)
        XMLHttpRequestObject = new ActiveXObject("Microsoft.XMLHTTP");
    if (XMLHttpRequestObject){
        XMLHttpRequestObject.open('POST','fileList/' + id);
        XMLHttpRequestObject.onreadystatechange = function(){
            if (XMLHttpRequestObject.readyState == 4 && XMLHttpRequestObject.status == 200){
                document.getElementById('file-list').innerHTML = XMLHttpRequestObject.responseText;
            }
        }
        XMLHttpRequestObject.send(null);
    }
}

function refreshTest(id){
    var XMLHttpRequestObject = false;
    if (window.XMLHttpRequest)
        XMLHttpRequestObject = new XMLHttpRequest();
    else if(window.ActiveXobject)
        XMLHttpRequestObject = new ActiveXObject("Microsoft.XMLHTTP");
    if (XMLHttpRequestObject){
        XMLHttpRequestObject.open('POST','/demand/' + id);
        XMLHttpRequestObject.onreadystatechange = function(){
            if (XMLHttpRequestObject.readyState == 4 && XMLHttpRequestObject.status == 200){
                document.getElementById('file-list').innerHTML = "<h6>Файл загружен</h6>";
            }
        }
        XMLHttpRequestObject.send(null);
    }
}