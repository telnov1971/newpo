<div class="form-group">
    <label>Статус:
    <#if demand??>
        ${demand.status.name}
    <#else>
        Новая
    </#if>
    </label>
</div>
