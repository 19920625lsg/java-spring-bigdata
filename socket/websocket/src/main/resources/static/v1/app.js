//stomp.js的使用参见文档： https://segmentfault.com/a/1190000006617344
// 还有： http://jmesnil.net/stomp-websocket/doc/
var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#notice").html("");
}

function connect() {
    var socket = new SockJS('/endpoint-websocket'); //连接上端点(基站)

    stompClient = Stomp.over(socket);			//用stom进行包装，规范协议
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        // 订阅主题，后面有消息会主动推送过来。第一个参数指定接收消息的接口(Controller里面的SendTo注解)
        stompClient.subscribe('/topic/game_chat', function (result) {
            console.info(result)
            showContent(JSON.parse(result.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    // 向服务端发送消息。第一个参数是服务端接收消息的接口(Controller里方法的MessageMapping注解)
    stompClient.send("/app/v1/chat", {}, JSON.stringify({'content': $("#content").val()}));
}

function showContent(body) {
    $("#notice").append("<tr><td>" + body.content + "</td> <td>" + new Date(body.time).toLocaleString() + "</td></tr>");
}


$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#connect").click(function () {
        connect();
    });
    $("#disconnect").click(function () {
        disconnect();
    });
    $("#send").click(function () {
        sendName();
    });
});

