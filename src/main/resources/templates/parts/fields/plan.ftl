<div class="form-group">
    <label>Рассорчка платежа: </label>
    <select id="plan" label="Рассорчка платежа" class="form-control" name="plan">
        <#list plans as plan>
            <option value="${plan.id}" label="${plan.name}"
                    <#if demand??>
                        <#if plan.id==demand.plan.id>
                            selected
                        </#if>
                    </#if>
            ></option>
        </#list>
    </select>
</div>
