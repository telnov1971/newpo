<div class="form-group">
    <label>Ценовая категория: </label>
    <select id="price" label="Ценовая категория" class="form-control" name="plan">
        <#list prices as price>
            <option value="${price.id}" label="${price.name}"
                    <#if demand??>
                        <#if price.id==demand.price.id>
                            selected
                        </#if>
                    </#if>
            ></option>
        </#list>
    </select>
</div>
