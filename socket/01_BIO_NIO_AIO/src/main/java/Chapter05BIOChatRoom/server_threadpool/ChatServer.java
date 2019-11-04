/***********************************************************
 * @Description : 
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/9/17 23:00
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter05BIOChatRoom.server_threadpool;

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