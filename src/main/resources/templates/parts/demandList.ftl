<!-- #include "security.ftl" -->

<div id="demand-list">
    <h3>Список запросов</h3>

<table class="table table-hover table-bordered">
    <thead class="thead-inverse">
    <tr>
        <th>Объект</th>
        <th>Адрес</th>
        <th>Мощность текущая</th>
        <th>Мощность требуемая</th>
        <th>Класс напряжения</th>
        <th>Категория надёжности</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <#list demands as demand>
        <tr>
            <td>${demand.object}</td>
            <td>${demand.adress}</td>
            <td>${demand.powerCur}</td>
            <td>${demand.powerDec}</td>
            <td>${demand.volt.name}</td>
            <td>${demand.safe.name}</td>
            <td class="table-warning"><a href="/demand/${demand.id}">Редактировать</a></td>
        </tr>
    <#else>
        Нет запросов
    </#list>
    </tbody>
</table>

</div>
