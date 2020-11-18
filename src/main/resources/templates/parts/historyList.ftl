<div id="history-list">
    <h3>Исторя изменений</h3>

    <table class="table table-hover table-bordered">
        <thead class="thead-inverse">
        <tr>
            <th>Дата</th>
            <th>Событие</th>
        </tr>
        </thead>
        <tbody>
        <#list history as event>
            <tr>
                <td>${event.createDate}</td>
                <td>${event.event}</td>
            </tr>
        </#list>
        </tbody>
    </table>

</div>
