# 第5章 实战：基于BIO的多人聊天室设计与实现

## 5.1 BIO多线程编程模型

![BIO多线程编程模型](https://img1.sycdn.imooc.com/szimg/5d7a01cf0001df5d12800720.jpg)

下面以多人聊天室为例子进行实战应用Socket和多线程

## 5.2 多人聊天室功能

### 多人聊天室需要满足的要求

+ 基于BIO模型
+ 支持多人同时在线
+ 每个用户的发言都被转发给其他在线用户

### 5.4 多人聊天室的UML时序图

> 时序图的基本概念见 https://github.com/19920625lsg/design-patterns/blob/master/java-design-patterns/src/main/java/chapter02umlbasic/01_UML分类与简述.md#时序图

![多人聊天室的UML时序图](bio-chatroom-sequence-diagram.png)

## 5.5 ChatServer:聊天室服务端

```java
package com.huawei.l00379880.mythread.Chapter05ChatRoom.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/***********************************************************
 * @note      : 
 * @author    : l00379880 梁山广
 * @version   : V1.0 at 2019/9/12 17:35
 ***********************************************************/
public class ChatServer {
    final String QUIT = "quit";
    private final int DEFAULT_PORT = 9999;
    /**
     * 解决消息中文乱码的问题
     */
    private final String CHARSET = "UTF-8";
    private ServerSocket serverSocket = null;

    /**
     * 存放socket连接的map,键是"ip:port",值是socket连接对象
     */
    private Map<String, Writer> connectedClients;

    public ChatServer() {
        connectedClients = new HashMap<>();
    }

    /**
     * 添加客户端连接。这个操作必须同步，否则可能会出状态冲突
     */
    public synchronized void addClient(Socket socket) throws IOException {
        if (socket != null) {
            String clientIp = socket.getInetAddress().getHostAddress();
            int clientPort = socket.getPort();
            String key = clientIp + ":" + clientPort;
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), CHARSET));
            connectedClients.put(key, writer);
            System.out.println("客户端【" + key + "】已连接到服务器");
        }
    }

    /**
     * 移除客户端连接。这个操作必须同步，否则可能会出状态冲突
     */
    public synchronized void removeClient(Socket socket) throws IOException {
        if (socket != null) {
            String clientIp = socket.getInetAddress().getHostAddress();
            int clientPort = socket.getPort();
            String key = clientIp + ":" + clientPort;
            // 删除指定ip和端口的client连接
            if (connectedClients.containsKey(key)) {
                // 关闭并移除socket连接
                connectedClients.get(key).close();
                connectedClients.remove(key);
                System.out.println("客户端【" + key + "】已断开连接");
            }
        }
    }

    /**
     * 向所有的客户端转发消息,加synchronized保证发消息的正确性
     */
    public synchronized void forwardMsg(Socket socket, String msg) throws IOException {
        assert socket != null;
        String clientIp = socket.getInetAddress().getHostAddress();
        int clientPort = socket.getPort();
        String srcKey = clientIp + ":" + clientPort;
        for (String key : connectedClients.keySet()) {
            if (!key.equals(srcKey)) {
                // 如果源和终点ip、端口相等，说明是同一个客户端，就不用发送了
                Writer writer = connectedClients.get(key);
                writer.write(msg);
                writer.flush();
            }
        }
    }

    /**
     * 用户是否要退出了
     */
    public boolean readyToQuit(String msg) {
        return QUIT.equals(msg);
    }

    /**
     * 关闭服务器
     */
    public void close() {
        if (serverSocket != null) {
            try {
                serverSocket.close();
                System.out.println("Server已经关闭！");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建并启动服务器端
     */
    public void start() {
        // 绑定监听端口
        try {
            System.out.println("我是服务端......");
            serverSocket = new ServerSocket(DEFAULT_PORT);
            System.out.println("启动服务器，监听端口：" + DEFAULT_PORT);
            while (true) {
                // 创建与指定客户端的链接
                Socket socket = serverSocket.accept();
                // 创建ChatHandler进程
                new Thread(new ChatHandler(this, socket)).start();
            }
        } catch (IOException e) {
            // 最主要的入口了。需要捕获异常了
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public static void main(String[] args) {
        ChatServer server = new ChatServer();
        server.start();
    }
}
```

### 5.6~5.7 ChatHandler:聊天室线程

```java
package com.huawei.l00379880.mythread.Chapter05ChatRoom.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/***********************************************************
 * @note      : 和一个客户端连接的线程
 * @author    : l00379880 梁山广
 * @version   : V1.0 at 2019/9/12 17:36
 ***********************************************************/
public class ChatHandler implements Runnable {
    private ChatServer server;
    private Socket socket;

    /**
     * 解决消息中文乱码的问题
     */
    private final String CHARSET = "UTF-8";

    /**
     * 收到这个消息说明该推出socket连接啦
     */
    final String QUIT = "quit";

    public ChatHandler(ChatServer server, Socket socket) {
        this.server = server;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // 存储新上线的用户
            server.addClient(socket);

            // 读取用户发送的消息
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), CHARSET));
            String clientIp = socket.getInetAddress().getHostAddress();
            int clientPort = socket.getPort();
            String key = clientIp + ":" + clientPort;
            String msg;

            while ((msg = reader.readLine()) != null) {
                String fwdMsg = new StringBuilder().append("客户端【").append(key).append("】说:").append(msg).append("\n").toString();
                System.out.print(fwdMsg);
                // 给所有的客户端转发这条消息,带上了发送者的信息
                server.forwardMsg(socket, fwdMsg);
                // 检查是否是退出命令,是地话就退出当前的socket连接
                if (server.readyToQuit(msg)){
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            // 客户端由于种种原因(正常退出或者异常退出)
            try {
                server.removeClient(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

```

## 5.8 ChatClient

```java
package com.huawei.l00379880.mythread.Chapter05ChatRoom.client;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/***********************************************************
 * @note      : 多人聊天室的客户端
 * @author    : l00379880 梁山广
 * @version   : V1.0 at 2019/9/15 11:15
 ***********************************************************/
public class ChatClient {
    final String QUIT = "quit";
    final String DEFAULT_SERVER_HOST = "127.0.0.1";
    final int DEFAULT_SERVER_PORT = 9999;
    /**
     * 解决消息中文乱码的问题
     */
    final String CHARSET = "UTF-8";

    Socket socket;
    BufferedReader reader;
    BufferedWriter writer;

    /**
     * 发送消息给服务器
     */
    public void send(String msg) throws IOException {
        if (!socket.isOutputShutdown()) {
            // 还没发送完就接着发
            writer.write(msg + "\n");
            writer.flush();
        }
    }

    /**
     * 从服务器接收消息
     */
    public String recv() throws IOException {
        String msg = null;
        if (!socket.isInputShutdown()) {
            msg = reader.readLine();
        }
        return msg;
    }

    /**
     * 用户是否要退出了
     */
    public boolean readyToQuit(String msg) {
        return QUIT.equals(msg);
    }

    /**
     * 关闭客户端
     */
    public void close() {
        if (writer != null) {
            try {
                // writer关闭就相当于客户端关闭了
                writer.close();
                System.out.println("Client已经关闭！");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建并启动客户端
     */
    public void start() {
        // 绑定监听端口
        try {
            System.out.println("我是客户端......");
            socket = new Socket(DEFAULT_SERVER_HOST, DEFAULT_SERVER_PORT);

            // 创建IO流
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), CHARSET));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), CHARSET));

            // 读取用户的输入
            new Thread(new UserInputHandler(this)).start();
            // 读取服务器转发地各种信息
            String msg;
            while ((msg=recv())!=null){
                System.out.println(msg);
            }
        } catch (IOException e) {
            // 最主要的入口了。需要捕获异常了
            e.printStackTrace();
        } finally {
            // 最终一定记得清理资源
            close();
        }
    }

    public static void main(String[] args) {
        ChatClient chatClient = new ChatClient();
        chatClient.start();
    }
}
```

## 5.8~5.10 UserInputHandler 用户输入处理

```java
package com.huawei.l00379880.mythread.Chapter05ChatRoom.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/***********************************************************
 * @note      : 用户输入处理类
 * @author    : l00379880 梁山广
 * @version   : V1.0 at 2019/9/15 12:00
 ***********************************************************/
public class UserInputHandler implements Runnable {
    private ChatClient chatClient;

    public UserInputHandler(ChatClient chatClient) {
        this.chatClient = chatClient;
    }


    @Override
    public void run() {
        try {
            // 等待用户输入信息
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String inputStr = consoleReader.readLine();
                // 向服务器发送消息
                chatClient.send(inputStr);
                // 判断是否该退出了
                if (chatClient.readyToQuit(inputStr)){
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

## 5.13 基于线程池的伪异步IO编程模型

![基于线程池的伪异步IO编程模型](https://img.mukewang.com/szimg/5d7ddabb0001849112800720.jpg)

## 5-14 使用多线程伪异步IO改进多人聊天室

```java
package com.huawei.l00379880.mythread.Chapter05ChatRoom.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/***********************************************************
 * @note      : 
 * @author    : l00379880 梁山广
 * @version   : V1.0 at 2019/9/12 17:35
 ***********************************************************/
public class ChatServer {
    final String QUIT = "quit";
    private final int DEFAULT_PORT = 9999;
    /**
     * 解决消息中文乱码的问题
     */
    private final String CHARSET = "UTF-8";

    /**
     * 线程池创建类
     */
    private ExecutorService executorService;
    private ServerSocket serverSocket = null;

    /**
     * 存放socket连接的map,键是"ip:port",值是socket连接对象
     */
    private Map<String, Writer> connectedClients;

    public ChatServer() {
        // 创建只允许10个客户端的线程池
        executorService = Executors.newFixedThreadPool(10);
        connectedClients = new HashMap<>();
    }

    /**
     * 添加客户端连接。这个操作必须同步，否则可能会出状态冲突
     */
    public synchronized void addClient(Socket socket) throws IOException {
        if (socket != null) {
            String clientIp = socket.getInetAddress().getHostAddress();
            int clientPort = socket.getPort();
            String key = clientIp + ":" + clientPort;
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), CHARSET));
            connectedClients.put(key, writer);
            System.out.println("客户端【" + key + "】已连接到服务器");
        }
    }

    /**
     * 移除客户端连接。这个操作必须同步，否则可能会出状态冲突
     */
    public synchronized void removeClient(Socket socket) throws IOException {
        if (socket != null) {
            String clientIp = socket.getInetAddress().getHostAddress();
            int clientPort = socket.getPort();
            String key = clientIp + ":" + clientPort;
            // 删除指定ip和端口的client连接
            if (connectedClients.containsKey(key)) {
                // 关闭并移除socket连接
                connectedClients.get(key).close();
                connectedClients.remove(key);
                System.out.println("客户端【" + key + "】已断开连接");
            }
        }
    }

    /**
     * 向所有的客户端转发消息,加synchronized保证发消息的正确性
     */
    public synchronized void forwardMsg(Socket socket, String msg) throws IOException {
        assert socket != null;
        String clientIp = socket.getInetAddress().getHostAddress();
        int clientPort = socket.getPort();
        String srcKey = clientIp + ":" + clientPort;
        for (String key : connectedClients.keySet()) {
            if (!key.equals(srcKey)) {
                // 如果源和终点ip、端口相等，说明是同一个客户端，就不用发送了
                Writer writer = connectedClients.get(key);
                writer.write(msg);
                writer.flush();
            }
        }
    }

    /**
     * 用户是否要退出了
     */
    public boolean readyToQuit(String msg) {
        return QUIT.equals(msg);
    }

    /**
     * 关闭服务器
     */
    public void close() {
        if (serverSocket != null) {
            try {
                serverSocket.close();
                System.out.println("Server已经关闭！");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建并启动服务器端
     */
    public void start() {
        // 绑定监听端口
        try {
            System.out.println("我是服务端......");
            serverSocket = new ServerSocket(DEFAULT_PORT);
            System.out.println("启动服务器，监听端口：" + DEFAULT_PORT);
            while (true) {
                // 创建与指定客户端的链接
                Socket socket = serverSocket.accept();
                // 在线程池中管理新的ChatHandler进程，维持在线程池的最大客户端数目以下
                executorService.execute(new ChatHandler(this, socket));
            }
        } catch (IOException e) {
            // 最主要的入口了。需要捕获异常了
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public static void main(String[] args) {
        ChatServer server = new ChatServer();
        server.start();
    }
}
```
