<div class="form-group">
    <label>Зона ответственности: </label>
    <select id="region" class="form-control" name="region">
        <#list regions as region>
            <option value="${region.id}" label="${region.name}"
                    <#if demand??>
                        <#if region.id==demand.region.id>
                            selected
                        </#if>
                    </#if>
            ></option>
        </#list>
    </select>
</div>
