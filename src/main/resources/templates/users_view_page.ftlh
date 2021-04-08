<!doctype html>
<html lang="en">
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<hr>
    <#list users as user>
        <p>${user.username}</p>
        <p>${user.email}</p>
        <hr>
    </#list>
<button id="more" onclick="add()">еще</button>
</body>
<script>
    let number;

    function add(){
        let data = {"number": 0};
        number++;
        $.ajax({
            type: "POST",
            dataType: "json",
            data: data,
            url: 'list',
            success: function (serverData) {
                alert(serverData);
            },
            error: function (error) {
                alert(error);
            }
        });
    };
</script>
</html>