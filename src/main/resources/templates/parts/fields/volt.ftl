<div class="form-group">
    <label>Класс напряжения: </label>
    <select id="volt" label="Класс напряжения" class="form-control" name="volt">
        <#list volts as volt>
            <option value="${volt.id}" label="${volt.name}"
                    <#if demand??>
                        <#if volt.id==demand.volt.id>
                            selected
                        </#if>
                    </#if>
            ></option>
        </#list>
    </select>
</div>
