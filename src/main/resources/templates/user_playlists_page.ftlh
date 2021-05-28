<!doctype html>
<html lang="en">
<head>
    <script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Playlists</title>
</head>
<body>
<#include "navbar.ftlh">
<hr>
<div id="wrapper">
    <div class="post" v-for="playlist in result.content" >
        <p>{{ playlist.name }}</p>
        <p>{{ playlist.user.username }}</p>
        <a v-bind:href="'/playlist/' + playlist.id">more</a>
        <hr>
    </div>
    <button v-on:click="navigate" v-bind:name="result.number-1" v-bind:hidden="result.first" id="prev">prev</button>
    <button v-on:click="navigate" v-bind:name="result.number+1" v-bind:hidden="result.last" id="next">next</button>
</div>

<script>
    let vm = new Vue({
        el: "#wrapper",
        data: {result: []},
        mounted() {
            axios.get('/getAllPlaylistsByUser').then(
                res => {
                    this.result = res.data;
                }
            );
            document.getElementById("prev").hidden = true;
        },
        methods:{
            navigate:
                function(event) {
                    let number = event.target.name;
                    axios.get('/getAllPlaylistsByUser?page=' + number).then(
                        res => {
                            this.result = res.data;
                        }
                    );
                }
        }
    });
</script>
</body>
</html>