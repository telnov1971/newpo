<div class="form-group">
    <label>Контактный телефон: </label>
    <input type="text" class="form-control ${(contactError??)?string('is-invalid', '')}"
           value="<#if demand??>${demand.contact}</#if>" name="contact" placeholder="Контактный телефон" />
    <#if contactError??>
        <div class="invalid-feedback">
            ${contactError}
        </div>
    </#if>
</div>
