function searchByDemand() {
    var id = document.getElementById("demand_id").value;
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        html = '<h1> END! </h1>';
        document.getElementById("filesList").innerHTML = html;
        //if (this.readyState == 4 && this.status == 200) {
            var files = JSON.parse(this.responseText);
            //var file = JSON.parse(files);
            var html = '<tr>\n' +
                '        <th>Файл</th>\n' +
                '        <th>Ссылка</th>\n' +
                '    </tr>';
            html = html + '<tr><td>' + files[0].name + '</td>\n' +
                '       <td>' +
                '           <a href="/file/' + files[0].link + '">\n' +
                '                 <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-download" fill="currentColor" xmlns="http://www.w3.org/2000/svg">\n' +
                '                      <path fill-rule="evenodd" d="M.5 9.9a.5.5 0 0 1 .5.5v2.5a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1v-2.5a.5.5 0 0 1 1 0v2.5a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2v-2.5a.5.5 0 0 1 .5-.5z"/>\n' +
                '                      <path fill-rule="evenodd" d="M7.646 11.854a.5.5 0 0 0 .708 0l3-3a.5.5 0 0 0-.708-.708L8.5 10.293V1.5a.5.5 0 0 0-1 0v8.793L5.354 8.146a.5.5 0 1 0-.708.708l3 3z"/>\n' +
                '                 </svg>\n' +
                '           </a>' +
                '       </td>\n' +
                '</tr>';
            //document.getElementById("filesList").innerHTML = html;
            document.body.innerHTML = html;
        //}
    };
    xhttp.open("GET", "http://localhost:8080/fileListRest?id=2", false);
    xhttp.send();
}

function test() {
//    var id = document.getElementById("demand_id").value;
//    var xhttp = new XMLHttpRequest();
    html = '<h1> END! </h1>';
    document.body.innerHTML = html;
//    xhttp.open("GET", "http://localhost:8080/fileListRest?id=2", false);
//    xhttp.send();
}
