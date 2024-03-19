<!DOCTYPE HTML>
<html>
<head>
    <title>${class.name} List</title>
    <style>
        ${r"<#include 'common.css'>"}
    </style>
</head>
<body>
<header>
    <a href="/index">home</a>
    <h2>${class.name} List</h2>
</header>
<#if class.page?? && class.page.getAll?c=="true">
    <div class="wrapper">
        <table border="1">
            <tr>
                <th>id</th>
                <#list properties as property>
                <th>${property.name}</th>
                </#list>
                <#if class.page.create?c=="true">
                <th>Delete</th>
                </#if>
            </tr>
            ${r'<#list '}${class.name?uncap_first}s as ${class.name?uncap_first}${r'>'}
            <tr>
                <td>
                    <a href="/${class.name?uncap_first}/${r"${"}${class.name?uncap_first}.id${r"}"}">${r"${"}${class.name?uncap_first}.id${r"}"}</a>
                </td>
                <#list properties as property>
                <#if property.type.name == "Integer" || property.type.name == "String">
                    <td>${r"${"}${class.name?uncap_first}.${property.name}${r"}"}</td>
                <#elseif property.type.name == "Boolean" || property.type.name == "boolean">
                    <td>${r"${"}${class.name?uncap_first}.${property.name}?string${r"}"}</td>
                <#elseif property.type.name == "Date" || property.type.name == "date">
                    <td>${r"${"}${class.name?uncap_first}.${property.name}?date${r"}"}</td>
                <#else>
                    ${r"<#if "}${class.name?uncap_first}.${property.name}${r"?has_content?c=='false'>
                        <td>field is null</td>"}
                    ${r"<#else>"}
                        <td>${r"${"}${class.name?uncap_first}.${property.name}.id${r"}"}</td>
                    ${r"</#if>"}
                </#if>
                </#list>
                <#if class.page.create?c=="true">
                <td>
                    <a href="/${class.name?uncap_first}/delete/${r"${"}${class.name?uncap_first}.id${r"}"}">Delete</a>
                </td>
                </#if>
            </tr>
            ${r'</#list>'}
        </table>
        <#if class.page.create?c=="true">
        <a href="/${class.name?uncap_first}/create">Create ${class.name?uncap_first}</a>
        </#if>
    </div>
<#else>
    <h1>
        This is auto generated page.
        In order to have ${class.name?uncap_first} list overview, you need to assign Page stereotype to ${class.name}.
    </h1>
</#if>
</body>
</html>