<!doctype html>
<html lang="en">
<head>
    <script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Playlist</title>
    <style>
        img {
            height: 100px;
            width: 100px;
        }

        .userImg {
            height: 25px;
            width: 25px;
        }
    </style>
</head>
<body>
<#include "navbar.ftlh">
    <div id="wrapper">
        <h1>${playlist.name}</h1>
        <p>Creator: ${playlist.user.username}</p>
        <a href="/editPlaylist/${playlist.id}">edit</a>
    </div>
<hr>
    <div id="videos">
        <div class="video" v-for="video in result.content" >
            <img v-bind:src="'/img/' + video.preview">
            <p>{{ video.name }}</p>
            <span>{{ video.creator.username }}</span> <img v-bind:src="'/img/' + video.creator.photo" class="userImg">
            <a v-bind:href="'/video/' + video.id">more</a>
            <hr>
        </div>
        <button v-on:click="navigate" v-bind:name="result.number-1" v-bind:hidden="result.first" id="prev">prev</button>
        <button v-on:click="navigate" v-bind:name="result.number+1" v-bind:hidden="result.last" id="next">next</button>
    </div>

    <script>

        let videos = new Vue({
            el: "#videos",
            data: {result: []},
            mounted() {
                axios.get('/videosByPlaylist?id=' + '${playlist.id}').then(
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
                        axios.get('/videosByPlaylist?page=' + number + '&id=' + '${playlist.id}').then(
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