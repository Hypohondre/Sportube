<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Change password</title>
</head>
<body>
<#include "navbar.ftlh">

<form method="post" action="/change">
    <p>Old password</p>
    <input type="text" name="oldPassword">
    <p>New password</p>
    <input type="text" name="newPassword">
    <p>Repeat new password</p>
    <input type="text" name="repeatNewPassword">
    <input type="submit">
</form>
</body>
</html>