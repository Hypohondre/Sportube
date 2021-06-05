<!doctype html>
<html lang="en">
<head>
    <script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Search</title>
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

<div id="videos">
        <input id="text" type="text" v-on:keydown="search">
        <div class="video" v-for="video in result.content" >
            <img v-bind:src="'/img/' + video.preview">
            <p>{{ video.name }}</p>
            <a v-bind:href="'/user/' + video.creator.id">{{ video.creator.username }}</a> <img v-bind:src="'/img/' + video.creator.photo" class="userImg">
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
                axios.get('/search').then(
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
                        let text = document.getElementById('text').value;
                        axios.get('/search?page=' + number + '&name=' + text).then(
                            res => {
                                this.result = res.data;
                            }
                        );
                    },
                search:
                    function (event) {
                        let text = event.target.value;
                        axios.get('/search?name=' + text).then(
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