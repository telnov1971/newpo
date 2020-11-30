<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page>

    <div>
        <#include "parts/demandEdit.ftl" />
    </div>

    <#if files??>
        <div>
            <#include "parts/fileList.ftl" />
        </div>
    </#if>

    <#if history??>
        <div>
            <#include "parts/historyList.ftl" />
        </div>
    </#if>

</@c.page>
