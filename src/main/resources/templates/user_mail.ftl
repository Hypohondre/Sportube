<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>User</title>
    <style>
        img {
            height: 30px;
            width: 30px;
        }
    </style>
</head>
<body>
    <p>${user.id}</p>
    <p>${user.username}</p>
    <p>${user.email}</p>
    <p>${user.password}</p>
    <p>${user.role}</p>
    <p>${user.state}</p>
    <p>${user.birth}</p>
    <img src="img/${user.photo}">
</body>
</html>