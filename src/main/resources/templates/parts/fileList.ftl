<div id="file-list">
    <script src="/static/fileupload.js"></script>
    <h3>Список файлов</h3>

    <table class="table table-hover table-bordered">
        <thead class="thead-inverse">
        <tr>
            <th>Файл</th>
            <th>Ссылка</th>
        </tr>
        </thead>
        <tbody>
        <#list files as file>
            <tr>
                <td>${file.name}</td>
                <td><a href="/file/${file.link}">
                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-download" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd" d="M.5 9.9a.5.5 0 0 1 .5.5v2.5a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1v-2.5a.5.5 0 0 1 1 0v2.5a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2v-2.5a.5.5 0 0 1 .5-.5z"/>
                            <path fill-rule="evenodd" d="M7.646 11.854a.5.5 0 0 0 .708 0l3-3a.5.5 0 0 0-.708-.708L8.5 10.293V1.5a.5.5 0 0 0-1 0v8.793L5.354 8.146a.5.5 0 1 0-.708.708l3 3z"/>
                        </svg>
                    </a>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>

    <div>
        <form action="/fileList/${demand.id}" method="post" enctype="multipart/form-data">
            <div>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <input type="file" name="file" id="customFile" class="form-control"/>
                <button class="btn btn-primary"
                        type="submit">
                    Загрузить
                </button>
            </div>
        </form>
    </div>

</div>