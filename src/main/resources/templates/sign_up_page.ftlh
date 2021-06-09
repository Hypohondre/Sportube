<!doctype html>
<html lang="en">
<#import "spring.ftl" as spring/>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<h2><a href="?lang=en">EN</a></h2>
<h2><a href="?lang=ru">RU</a></h2>
<h1><@spring.message 'sign_up_page.title'/></h1>
<#if errors?has_content>
    <#list errors as error>
        <div>${error}</div>
    </#list>
</#if>
    <form method="post" action="/signUp" enctype="multipart/form-data">
        <label><@spring.message 'sign_up_page.email'/></label>
        <input type="text" name="email">
        <label><@spring.message 'sign_up_page.password'/></label>
        <input type="password" name="password">
        <label><@spring.message 'sign_up_page.username'/></label>
        <input type="text" name="username">
        <label><@spring.message 'sign_up_page.birth'/></label>
        <input type="date" name="birth">
        <input type="tel" name="phone">
        <input type="submit">
    </form>
</body>
</html>