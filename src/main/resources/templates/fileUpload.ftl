<!--#import "parts/common.ftl" as c-->
<#include "parts/security.ftl">

<!-- @c.page -->

    <h4>Id: ${demand.id}</h4>
    <#if user??>${name}</#if>

    <div>
        <form method="post" enctype="multipart/form-data">
            <div>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <input type="file" name="file" id="customFile" class="form-control"/>
                <button class="btn btn-primary"
                        type="submit">
                    <!-- onsubmit="refresh(${demand.id})" -->
                    Обновить
                </button>
            </div>

        </form>
    </div>

<!-- /@c.page -->
