<#include "security.ftl">

<div>
    <div class="form-group mt-3">
        <form action="
            <#if demand??>
                demand/${demand.id}
            <#else>
                new
            </#if>"
              method="post" enctype="multipart/form-data">
            <#if demand??>
                <label>Дата создания: ${demand.createDate}</label>
            </#if>

            <#include "fields/status.ftl" />

            <#include "fields/declarant.ftl" />
            <#include "fields/contact.ftl" />
            <#include "fields/requisite.ftl" />
            <#include "fields/adressur.ftl" />
            <#include "fields/adressfact.ftl" />

            <#include "fields/object.ftl" />
            <#include "fields/adress.ftl" />

            <#include "fields/powercur.ftl" />
            <#include "fields/powerdec.ftl" />
            <#include "fields/powermax.ftl" />

            <#include "fields/volt.ftl" />
            <#include "fields/safe.ftl" />
            <#include "fields/region.ftl" />

            <#include "fields/price.ftl" />
            <#include "fields/plan.ftl" />
            <#include "fields/send.ftl" />
            <#include "fields/garant.ftl" />


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
                <button class="btn btn-secondary"><a href=".">Отмена</a></button>
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

