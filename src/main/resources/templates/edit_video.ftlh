<!doctype html>
<html lang="en">
<head>
    <script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Edit video</title>
</head>
<body>
<#include "navbar.ftlh">
<div id="wrapper">
    <form method="post" action="/video/${video.id}" enctype="multipart/form-data">
        <label>Name
            <input type="text" name="name" value="${video.name}"></label>
        <label>Description
            <textarea name="description" >${video.description}</textarea></label>
        <label>Preview
            <input type="file" name="preview"></label>
        <input type="submit">
    </form>
</div>
</body>
</html>