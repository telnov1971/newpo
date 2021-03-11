<div class="form-group">
    <label>Заявитель: </label>
    <textarea type="textarea" class="form-control ${(objectError??)?string('is-invalid', '')}"
           rows="3" name="declarant" placeholder="Заявитель" >
    <#if demand??>${demand.declarant}</#if>
    <#if textError??>
        <div class="invalid-feedback">
            ${objectError}
        </div>
    </#if>
    </textarea>
</div>
