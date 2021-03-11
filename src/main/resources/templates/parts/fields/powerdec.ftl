<div class="form-group">
    <label>Мощность требуемая: </label>
    <input type="number" class="form-control"
           value="<#if demand??>${demand.powerDec}</#if>"
           name="powerDec"/>
    <#if powerDecError??>
        <div class="invalid-feedback">
            ${powerDecError}
        </div>
    </#if>
</div>
