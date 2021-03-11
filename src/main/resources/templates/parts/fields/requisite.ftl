<div class="form-group">
    <label>Реквизиты заявителя: </label>
    <textarea type="textarea" class="form-control ${(requisiteError??)?string('is-invalid', '')}" rows="3"
              name="requisite" placeholder="Реквизиты заявителя">
    <#if demand??>${demand.requisite}</#if>
    <#if requisiteError??>
        <div class="invalid-feedback">
            ${requisiteError}
        </div>
    </#if>
    </textarea>
</div>
