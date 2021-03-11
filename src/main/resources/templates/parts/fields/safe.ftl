<div class="form-group">
    <label>Категория надёжности: </label>
    <select id="safe" label="Категория надёжности" class="form-control" name="safe">
        <#list safes as safe>
            <option value="${safe.id}" label="${safe.name}"
                    <#if demand??>
                        <#if safe.id==demand.safe.id>
                            selected
                        </#if>
                    </#if>
            ></option>
        </#list>
    </select>
</div>
