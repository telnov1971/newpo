<div class="form-group">
    <label>Объект: </label>
    <textarea class="form-control ${(objectError??)?string('is-invalid', '')}"
           name="object" placeholder="Объект" wrap="soft">
    <#if demand??>${demand.object}</#if>
    <#if objectError??>
        <div class="invalid-feedback">
            ${objectError}
        </div>
    </#if>
    </textarea>
</div>
