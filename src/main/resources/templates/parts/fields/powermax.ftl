<div class="form-group">
    <label>Мощность максимальная: </label>
    <input type="number" class="form-control"
           value="<#if demand??>${demand.powerMax}</#if>"
           name="powerMax"/>
    <#if powerMaxError??>
        <div class="invalid-feedback">
            ${powerMaxError}
        </div>
    </#if>
</div>
