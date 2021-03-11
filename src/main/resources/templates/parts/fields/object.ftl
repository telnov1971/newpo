<div class="form-group">
    <label>Объект: </label>
    <textarea type="text" class="form-control ${(objectError??)?string('is-invalid', '')}"
           rows="3" name="object" placeholder="Объект" >
    <#if demand??>${demand.object}</#if>
    <#if objectError??>
        <div class="invalid-feedback">
            ${objectError}
        </div>
    </#if>
    </textarea>
</div>
