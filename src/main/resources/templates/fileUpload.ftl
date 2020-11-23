<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>

    <h4>Id: ${demand.id}</h4>
    <#if user??>${name}</#if>
    <p></p>

    <div>
        <form method="get">
            <div id="filesList">
                Здесь
            </div>
            <div></div>
            <div>
                <input type="hidden" id="demand_id" value="${demand.id}" />
                <button class="btn btn-primary"
                        onclick="searchByDemand()">
                    Обновить
                </button>
            </div>
        </form>
    </div>

<script src="/static/fileupload.js"></script>
</@c.page>
