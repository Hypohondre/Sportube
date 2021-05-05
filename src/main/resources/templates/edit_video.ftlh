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
    <form method="post" v-bind:action="'/video/' + result.id" enctype="multipart/form-data">
        <label>Name
            <input type="text" name="name" v-bind:value="result.name"></label>
        <label>Description
            <textarea name="description" v-bind:value="result.description"></textarea></label>
        <label>Preview
            <input type="file" name="preview"></label>
        <input type="submit">
    </form>
</div>

<script>
    let vm = new Vue({
        el: "#wrapper",
        data: {result:[]},
        mounted() {
            axios.get('/videos/' + getAllUrlParams().id).then(
                res => {
                    this.result = res.data;
                }
            );
        }
    });

    function getAllUrlParams(url) {
        var queryString = url ? url.split('?')[1] : window.location.search.slice(1);
        var obj = {};
        if (queryString) {
            queryString = queryString.split('#')[0];
            var arr = queryString.split('&');
            for (var i=0; i<arr.length; i++) {
                var a = arr[i].split('=');
                var paramNum = undefined;
                var paramName = a[0].replace(/\[\d*\]/, function(v) {
                    paramNum = v.slice(1,-1);
                    return '';
                });
                var paramValue = typeof(a[1])==='undefined' ? true : a[1];
                paramName = paramName.toLowerCase();
                paramValue = paramValue.toLowerCase();
                if (obj[paramName]) {
                    if (typeof obj[paramName] === 'string') {
                    }
                    if (typeof paramNum === 'undefined') {
                        obj[paramName].push(paramValue);
                    }
                    else {
                        obj[paramName][paramNum] = paramValue;
                    }
                }
                else {
                    obj[paramName] = paramValue;
                }
            }
        }
        return obj;
    }
</script>
</body>
</html>