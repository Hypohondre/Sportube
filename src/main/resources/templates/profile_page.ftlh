<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <a href="/logout">logout</a> <br>
    ${user.username} <br>
    ${user.email} <br>
    ${user.birth} <br>
    ${user.role} <br>
    ${user.state} <br>
    <#if user.photo??>
    <img src="img/${user.photo}" alt="/img/default.png">
    <#else>
    <img src="img/default.png" alt="/img/default.png">
    </#if>

    <form method="post" action="/profile" enctype="multipart/form-data">
        <input type="file" name="file"> <input type="submit">
    </form>
</body>
</html>