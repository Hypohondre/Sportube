<!doctype html>
<html lang="en">
<head>
    <script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Video</title>
    <style>
        video {
            height: 500px;
            width: 1000px;
        }
    </style>
</head>
<body>
<#include "navbar.ftlh">
    <div id="wrapper">
        <video src="http://localhost:8080/getVideo/${video.file}" controls>
        </video>
        <p>${video.name}</p>
        <p>size: ${video.size}</p>
        <p>creator: ${video.creator.username}</p>
        <p>description: ${video.description}</p>
        <a href="/editVideo/${video.id}">edit</a>
    </div>
</body>
</html>