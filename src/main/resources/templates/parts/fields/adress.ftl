<div class="form-group">
    <label>Адрес: </label>
    <textarea type="text" class="form-control" rows="3"
           name="adress" placeholder="Адресс">
    <#if demand??>${demand.adress}</#if>
    <#if adressError??>
        <div class="invalid-feedback">
            ${adressError}
        </div>
    </#if>
    </textarea>
</div>
