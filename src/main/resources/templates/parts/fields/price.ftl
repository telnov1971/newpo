<div class="form-group">
    <label>Ценовая категория: </label>
    <select id="price" label="Ценовая категория" class="form-control" name="price">
        <#list prices as price>
            <option value="${price.id}" label="${price.name}"
                    <#if demand??>
                        <#if price.id==demand.price.id>
                            selected
                        </#if>
                    <#else>
                        <#if price.id==1>
                            selected
                        </#if>
                    </#if>
            ></option>
        </#list>
    </select>
</div>
