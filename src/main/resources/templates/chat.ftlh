<!DOCTYPE html>
<html>
<head>
    <title>Hello WebSocket</title>
    <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
</head>
<body>
        <input type="text" id="text">
        <button onclick="sendMessage()">send</button>
<p id="chat">Messages:</p>
</body>

<script>
        var socket = new SockJS('/websocket');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/chat', function (greeting) {
                showGreeting(JSON.parse(greeting.body));
            });
        });

    function showGreeting(message) {
        let div = document.createElement('div');
        div.innerHTML = "<br>" + message.username + " : " + message.text;
        document.getElementById("chat").append(div);
    }

    function sendMessage() {
        stompClient.send("/app/chat", {}, JSON.stringify({"text":document.getElementById("text").value}));
    }
</script>
</html>