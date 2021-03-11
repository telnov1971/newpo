<div class="form-group">
    <label>Мощность текущая: </label>
    <input type="number" class="form-control"
           value="<#if demand??>${demand.powerCur}</#if>"
           name="powerCur"/>
    <#if adressError??>
        <div class="invalid-feedback">
            ${adressError}
        </div>
    </#if>
</div>
