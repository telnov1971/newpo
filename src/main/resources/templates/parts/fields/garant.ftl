<div class="form-group">
    <label>Гарантирующий поставщик: </label>
    <select id="garant" label="Гарантирующий поставщик" class="form-control" name="garant">
        <#list garants as garant>
            <option value="${garant.id}" label="${garant.name}"
                    <#if demand??>
                        <#if garant.id==demand.garant.id>
                            selected
                        </#if>
                    </#if>
            ></option>
        </#list>
    </select>
</div>
