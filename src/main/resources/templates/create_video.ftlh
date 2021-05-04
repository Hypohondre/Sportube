<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Create video</title>
</head>
<body>
    <form method="post" action="/videos" enctype="multipart/form-data">
        <label>Name
        <input type="text" name="name"></label>
        <label>Description
        <textarea name="description"></textarea></label>
        <label>Preview
        <input type="file" name="preview"></label>
        <label>Video
        <input type="file" name="video"></label>
        <input type="submit">
    </form>
</body>
</html>