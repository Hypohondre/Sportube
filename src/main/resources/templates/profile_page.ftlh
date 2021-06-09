<!doctype html>
<html lang="en">
<head>
    <script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <style>
        img {
            height: 200px;
            width: 200px;
            border-radius: 100px;
        }

        #wrapper {
            margin-top: 100px;
        }
    </style>
</head>
<body>
<#include "navbar.ftlh">
<div id="wrapper">
        <p><div class="userInfo">Username : ${ user.username }</div></p>
    <p><div class="userInfo">Email : ${ user.email }</div></p>
    <p><div class="userInfo">Birth : ${ user.birth }</div></p>
    <p><div class="userInfo">Role : ${ user.role }</div></p>
    <p> <div class="userInfo">State : ${ user.state }</div></p>
    <img src="img/${user.photo}">
    <form method="post" action="/changePhoto" enctype="multipart/form-data">
        <input type="file" name="file">
        <input type="submit">
    </form>
    <a href="/editProfile">edit</a>
</div>

<a href="/sendMail">send user to email</a>
</body>
</html>