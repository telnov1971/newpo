
<div>
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label>Объект: </label>
                <input type="text" class="form-control ${(objectError??)?string('is-invalid', '')}"
                       value="<#if demand??>${demand.object}</#if>" name="object" placeholder="Объект" />
                <#if textError??>
                    <div class="invalid-feedback">
                        ${objectError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <label>Адрес: </label>
                <input type="text" class="form-control"
                       value="<#if demand??>${demand.adress}</#if>" name="tag" placeholder="Адресс" />
                <#if adressError??>
                    <div class="invalid-feedback">
                        ${adressError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <label>Мощность текущая: </label>
                <input type="text" class="form-control"
                       value="<#if demand??>${demand.powerCur}</#if>" name="tag" placeholder="Число" />
                <#if adressError??>
                    <div class="invalid-feedback">
                        ${adressError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <label>Мощность требуемая: </label>
                <input type="text" class="form-control"
                       value="<#if demand??>${demand.powerDec}</#if>" name="tag" placeholder="Число" />
                <#if adressError??>
                    <div class="invalid-feedback">
                        ${adressError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <label>Класс напряжения: </label>
                <select id="volt" label="Класс напряжения" class="form-control">
                    <#list volts as volt>
                        <#if volt.name==demand.volt.name>
                            <option selected>
                        <#else>
                            <option>
                        </#if>
                                ${volt.name}
                        </option>
                    </#list>
                </select>
            </div>
            <div class="form-group">
                <label>Категория надёжности: </label>
                <select id="safe" label="Категория надёжности" class="form-control">
                    <#list safes as safe>
                        <#if safe.name==demand.safe.name>
                            <option selected>
                        <#else>
                            <option>
                        </#if>
                                ${safe.name}
                        </option>
                    </#list>
                </select>
            </div>
            <!-- div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file" id="customFile" />
                    <label class="custom-file-label" for="customFile">Выбор файла</label>
                </div>
            </div -->
            <!-- input type="hidden" name="_csrf" value="${_csrf.token}" /-->
            <input type="hidden" name="id" value="<#if demand??>${demand.id}</#if>" />
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Отправить</button>
            </div>
        </form>
    </div>
</div>

