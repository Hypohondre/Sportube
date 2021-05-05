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
    <form method="post" v-bind:action="'/users/' + result.id">
        <label> <p>Name</p>
            <input type="text" name="username" v-bind:value="result.username"></label>
        <label> <p>Email</p>
            <input type="text" name="email" v-bind:value="result.email"></label>
        <label> <p>Birth</p>
            <input type="date" name="birth" v-bind:value="result.birth"></label>
        <input type="submit">
    </form>
</div>

    <script>
        let vm = new Vue({
            el: "#wrapper",
            data: {result:[]},
            mounted() {
                axios.get('/getProfile').then(
                    res => {
                        this.result = res.data;
                    }
                );
            }
        });
    </script>
</body>
</html>