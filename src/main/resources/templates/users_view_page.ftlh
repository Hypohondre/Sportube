<!doctype html>
<html lang="en">
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>

    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<hr>
    <div id="wrapper">
        <div class="post" v-for="user in result.content" >
            <p>{{ user.username }}</p>
            <p>{{ user.email }}</p>
            <hr>
        </div>
        <button v-on:click="navigate" v-bind:name="result.number-1" v-bind:hidden="result.first" id="prev">prev</button>
        <button v-on:click="navigate" v-bind:name="result.number+1" v-bind:hidden="result.last" id="next">next</button>
    </div>
</body>
<script>
    let vm = new Vue({
        el: "#wrapper",
        data: {result: []},
        mounted() {
            axios.get('/users').then(
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
                    axios.get('users/' + number).then(
                        res => {
                            this.result = res.data;
                        }
                    );
                }
        }
    });
</script>
</html>