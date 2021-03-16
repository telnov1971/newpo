<div class="form-group">
    <label>Юридический адрес заявителя: </label>
    <textarea type="text" class="form-control ${(adressUrError??)?string('is-invalid', '')}"
           rows="3" name="adressUr" placeholder="Юридический адрес заявителя" >
    <#if demand??>${demand.adressUr}</#if>
    <#if adressUrError??>
        <div class="invalid-feedback">
            ${adressUrError}
        </div>
    </#if>
    </textarea>
</div>
