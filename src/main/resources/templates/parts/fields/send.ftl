<div class="form-group">
    <label>Получение договора: </label>
    <select id="send" label="Получение договора" class="form-control" name="send">
        <#list sends as send>
            <option value="${send.id}" label="${send.name}"
                    <#if demand??>
                        <#if send.id==demand.send.id>
                            selected
                        </#if>
                    <#else>
                        <#if send.id==1>
                            selected
                        </#if>
                    </#if>
            ></option>
        </#list>
    </select>
</div>
