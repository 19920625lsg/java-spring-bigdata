# [课程地址](http://edu.51cto.com/course/13306.html)

## [Spring官方地址](https://spring.io/guides/gs/messaging-stomp-websocket/)

## WebSocket通信原理
![WX20180808-232850.png](https://i.loli.net/2018/08/08/5b6b0c5ee329f.png)

## StompJS文档
> [https://segmentfault.com/a/1190000006617344](https://segmentfault.com/a/1190000006617344)
> [http://jmesnil.net/stomp-websocket/doc/](http://jmesnil.net/stomp-websocket/doc/)

## 监听器一共有以下几类，自己酌情使用
+              SessionConnectedEvent  ： 建立连接后
+               SessionConnectEvent    ： 建立连接前
+               SessionDisconnectEvent :  断开连接后边
+               SessionSubscribeEvent  ： 消息订阅时
+               SessionUnsubscribeEvent：取消消息订阅时