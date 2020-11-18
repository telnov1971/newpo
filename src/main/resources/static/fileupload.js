function refresh(id){
    var XMLHttpRequestObject = false;
    if (window.XMLHttpRequest)
        XMLHttpRequestObject = new XMLHttpRequest();
    else if(window.ActiveXobject)
        XMLHttpRequestObject = new ActiveXObject("Microsoft.XMLHTTP");
    if (XMLHttpRequestObject){
        XMLHttpRequestObject.open('GET','fileList/' + id);
        XMLHttpRequestObject.onreadystatechange = function(){
            if (XMLHttpRequestObject.readyState == 4 && XMLHttpRequestObject.status == 200){
                document.getElementById('file-list').innerHTML = XMLHttpRequestObject.responseText;
            }
        }
        XMLHttpRequestObject.send(null);
    }
}