<#include "security.ftl">

<div>
    <div class="form-group mt-3">
        <form action="
            <#if demand??>
                /demand/{Id}
            <#else>
                /new
            </#if>"
              method="post" enctype="multipart/form-data">
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
                       value="<#if demand??>${demand.adress}</#if>" name="adress" placeholder="Адресс" />
                <#if adressError??>
                    <div class="invalid-feedback">
                        ${adressError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <label>Мощность текущая: </label>
                <input type="number" class="form-control"
                       value="<#if demand??>${demand.powerCur}</#if>"
                       name="powerCur"/>
                <#if adressError??>
                    <div class="invalid-feedback">
                        ${adressError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <label>Мощность требуемая: </label>
                <input type="number" class="form-control"
                       value="<#if demand??>${demand.powerDec}</#if>"
                       name="powerDec"/>
                <#if adressError??>
                    <div class="invalid-feedback">
                        ${adressError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <label>Класс напряжения: </label>
                <select id="volt" label="Класс напряжения" class="form-control" name="volt">
                    <#list volts as volt>
                        <#if demand??>
                            <#if volt.name==demand.volt.name>
                                <option selected>
                            <#else>
                                <option>
                            </#if>
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
                <select id="safe" label="Категория надёжности" class="form-control" name="safe">
                    <#list safes as safe>
                        <#if demand??>
                            <#if safe.name==demand.safe.name>
                                <option selected>
                            <#else>
                                <option>
                            </#if>
                        <#else>
                            <option>
                        </#if>
                                ${safe.name}
                        </option>
                    </#list>
                </select>
            </div>
            <div class="form-group">
                <label>Файл для загрузки: </label>
                <input type="file" name="file1" id="inFile1" class="form-control" />
                <input type="file" name="file2" id="inFile2" class="form-control" />
                <input type="file" name="file3" id="inFile3" class="form-control" />
                <input type="file" name="file4" id="inFile4" class="form-control" />
                <input type="file" name="file5" id="inFile5" class="form-control" />
                <input type="file" name="file6" id="inFile6" class="form-control" />
                <input type="file" name="file7" id="inFile7" class="form-control" />
                <input type="file" name="file8" id="inFile8" class="form-control" />
                <input type="file" name="file9" id="inFile9" class="form-control" />
                <input type="file" name="file10" id="inFile10" class="form-control" />
            </div>
            <input type="hidden" name="id" value="<#if demand??>${demand.id}</#if>" />
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Отправить</button>
                <button class="btn btn-secondary"><a href="/">Отмена</a></button>
                <input type="button" class="btn btn-secondary" onclick="addFile();" value="Добавить файл..." />
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <script>
                for(i=2;i<=10;i++){
                    ind = "#inFile" + i;
                    $(ind).css("display","none");
                }
                var countFile = 1;
                function addFile() {
                    if(countFile==10) alert("Вы можете добавить только 10 файлов до отправки данных на сервер");
                    ind = "#inFile" + (countFile + 1);
                    $(ind).css("display","block");
                    countFile = countFile + 1;
                }
            </script>
        </form>
    </div>
</div>

