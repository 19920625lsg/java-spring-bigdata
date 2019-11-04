/***********************************************************
 * @Description : 基于NIO的聊天室
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/9/17 23:00
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter07NIOChatRoom.server;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Set;

/***********************************************************
 * @note      :
 * @author    : l00379880 梁山广
 * @version   : V1.0 at 2019/9/12 17:35
 ***********************************************************/
public class ChatServer {
    final String QUIT = "quit";
    private final int BUFFER_SIZE = 1024;
    /**
     * 解决消息中文乱码的问题
     */
    private final String CHARSET = "UTF-8";

    private Charset charset = Charset.forName(CHARSET);

    /**
     * 基于NIO的Channel的ServerSocket
     */
    private ServerSocketChannel serverSocketChannel;

    /**
     * 基于selector把多线程改成单线程
     */
    private Selector selector;

    /**
     * 读缓存
     */
    private ByteBuffer readBuffer = ByteBuffer.allocate(BUFFER_SIZE);

    /**
     * 写缓存,把消息转发给其他客户端，写入Channel
     */
    private ByteBuffer writeBuffer = ByteBuffer.allocate(BUFFER_SIZE);

    /**
     * 自定义的服务端端口
     */
    private int port;

    public ChatServer(int port) {
        this.port = port;
    }

    /**
     * 用户是否要退出了
     */
    public boolean readyToQuit(String msg) {
        return QUIT.equals(msg);
    }


    /**
     * 创建并启动服务器端
     */
    public void start() {
        try {
            serverSocketChannel = ServerSocketChannel.open();
            // 设置为非阻塞调用
            serverSocketChannel.configureBlocking(false);
            // 绑定端口
            serverSocketChannel.socket().bind(new InetSocketAddress(port));
            // 处理selector
            selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("启动服务器，监听端口：" + port + "......");

            while (true) {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                // 轮询事件
                for (SelectionKey selectionKey : selectionKeys) {
                    // 处理被触发的事件
                    handles(selectionKey);
                }
                selectionKeys.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(selector);
        }
    }

    private String getClientInfo(SocketChannel client) {
        String clientIp = client.socket().getInetAddress().getHostAddress();
        int clientPort = client.socket().getPort();
        return "客户端【" + clientIp + ":" + clientPort + "】";
    }

    private void handles(SelectionKey selectionKey) throws IOException {
        // ACCEPT事件--和客户端建立了连接
        if (selectionKey.isAcceptable()) {
            ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
            SocketChannel client = server.accept();
            // 设置为非阻塞
            client.configureBlocking(false);
            client.register(selector, SelectionKey.OP_READ);
            System.out.println(getClientInfo(client) + "已连接");
        } else if (selectionKey.isReadable()) {
            // READ事件--客户端发送了消息
            SocketChannel client = (SocketChannel) selectionKey.channel();
            // 接收并转发消息
            String fwdMsg = recv(client);
            if (fwdMsg.isEmpty()) {
                // 消息为空。客户端出现了异常
                selectionKey.cancel();
                selector.wakeup();
            } else {
                // 打印出服务端转发的消息
                System.out.println(getClientInfo(client) + ":" + fwdMsg);
                forwardMsg(client, fwdMsg);
                // 检测用户退出
                if (readyToQuit(fwdMsg)) {
                    selectionKey.cancel();
                    selector.wakeup();
                    System.out.println(getClientInfo(client) + "已断开");
                }
            }
        }

    }

    /**
     * 转发消息给所有在线地客户端
     *
     * @param client 客户端
     * @param fwdMsg 要转发的消息
     */
    private void forwardMsg(SocketChannel client, String fwdMsg) throws IOException {
        Set<SelectionKey> keys = selector.keys();
        for (SelectionKey key : keys) {
            Channel connectedClient = key.channel();
            if (connectedClient instanceof ServerSocketChannel) {
                continue;
            }
            if (key.isValid() && !client.equals(connectedClient)) {
                // 有效的通道而且不是自己才进行消息转发
                writeBuffer.clear();
                writeBuffer.put(charset.encode(getClientInfo(client) + ":" + fwdMsg));
                // 状态翻转，读变写，写变读
                writeBuffer.flip();
                while (writeBuffer.hasRemaining()) {
                    ((SocketChannel)connectedClient).write(writeBuffer);
                }
            }
        }
    }

    private String recv(SocketChannel client) throws IOException {
        readBuffer.clear();
        // 读出的内容存到readBuffer中
        while (client.read(readBuffer) > 0) {
        }
        readBuffer.flip();

        return String.valueOf(charset.decode(readBuffer));
    }

    /**
     * 关闭资源类
     */
    private void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ChatServer server = new ChatServer(9527);
        server.start();
    }
}
