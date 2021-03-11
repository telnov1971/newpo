<div class="form-group">
    <label>Фактический адрес заявителя: </label>
    <textarea type="textarea" class="form-control ${(adressFactError??)?string('is-invalid', '')}"
           rows="3" name="adressFact" placeholder="Фактический адрес заявителя">
    <#if demand??>${demand.adressFact}</#if>
    <#if adressFactError??>
        <div class="invalid-feedback">
            ${adressFactError}
        </div>
    </#if>
    </textarea>
</div>
