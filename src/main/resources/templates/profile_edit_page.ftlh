<!doctype html>
<html lang="en">
<head>
    <script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Edit profile</title>
</head>
<body>
<#include "navbar.ftlh">
<div id="wrapper">
    <form method="post" action="/users/${user.id}">
        <label> <p>Name</p>
            <input type="text" name="username" value="${user.username}"></label>
        <label> <p>Email</p>
            <input type="text" name="email" value="${user.email}"></label>
        <label> <p>Birth</p>
            <input type="date" name="birth" value="${user.birth?iso_utc}"></label>
        <input type="submit">
    </form>

    <a href="/changePassword">
        <button>change password</button>
    </a>
</div>
</body>
</html>