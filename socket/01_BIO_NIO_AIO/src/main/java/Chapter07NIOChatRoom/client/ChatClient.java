/***********************************************************
 * @Description : 
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/9/17 23:01
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter07NIOChatRoom.client;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedSelectorException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Set;

/***********************************************************
 * @note      : 多人聊天室的客户端
 * @author    : l00379880 梁山广
 * @version   : V1.0 at 2019/9/15 11:15
 ***********************************************************/
public class ChatClient {
    final String QUIT = "quit";
    /**
     * 解决消息中文乱码的问题
     */
    final String CHARSET = "UTF-8";

    private final int BUFFER_SIZE = 1024;

    private Charset charset = Charset.forName(CHARSET);
    private String host;
    private int port;
    private SocketChannel client;
    Socket socket;
    /**
     * 读缓存
     */
    private ByteBuffer readBuffer = ByteBuffer.allocate(BUFFER_SIZE);

    /**
     * 写缓存,把消息转发给其他客户端，写入Channel
     */
    private ByteBuffer writeBuffer = ByteBuffer.allocate(BUFFER_SIZE);

    private Selector selector;

    public ChatClient(String host, int port) {
        this.host = host;
        this.port = port;
    }


    /**
     * 用户是否要退出了
     */
    public boolean readyToQuit(String msg) {
        return QUIT.equals(msg);
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

    /**
     * 创建并启动客户端
     */
    public void start() {
        try {
            client = SocketChannel.open();
            client.configureBlocking(false);

            selector = Selector.open();
            client.register(selector, SelectionKey.OP_CONNECT);
            client.connect(new InetSocketAddress(host, port));
            while (true) {
                // 监听通道事件
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                for (SelectionKey selectionKey : selectionKeys) {
                    handles(selectionKey);
                }
                selectionKeys.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }catch (ClosedSelectorException e){
            // 用户正常退出，不需要作额外处理
        }finally {
            close(selector);
        }
    }

    /**
     * 处理客户端事件
     */
    private void handles(SelectionKey selectionKey) throws IOException {
        // Connect事件--连接就绪事件
        if (selectionKey.isConnectable()) {
            SocketChannel client = (SocketChannel) selectionKey.channel();
            if (client.isConnectionPending()) {
                // 已经连接就绪
                client.finishConnect();
                // 处理用户的输出
                new Thread(new UserInputHandler(this)).start();
            }
            client.register(selector, SelectionKey.OP_READ);
        }else if (selectionKey.isReadable()){
            // Read事件---服务器转发消息
            SocketChannel client = (SocketChannel) selectionKey.channel();
            String msg = recv(client);
            if (msg.isEmpty()){
                 // 连接出现异常
                close(selector);
            }else {
                System.out.println(msg);
            }
        }

    }

    /**
     * 接收服务端的消息
     * @param client
     * @return
     */
    private String recv(SocketChannel client) throws IOException {
        readBuffer.clear();
        // 读出的内容存到readBuffer中
        while (client.read(readBuffer) > 0) {
        }
        readBuffer.flip();

        return String.valueOf(charset.decode(readBuffer));
    }

    /**
     * 发送消息
     * @param msg 消息内容
     */
    public void send(String msg) throws IOException {
        if (msg.isEmpty()) {
            return;
        }
        writeBuffer.clear();
        writeBuffer.put(charset.encode(msg));
        // 读写切换
        writeBuffer.flip();
        while (writeBuffer.hasRemaining()) {
            client.write(writeBuffer);
        }

        // 检查用户退出的情况
        if (readyToQuit(msg)){
            close(selector);
        }
    }

    public static void main(String[] args) {
        ChatClient chatClient = new ChatClient("127.0.0.1", 9527);
        chatClient.start();
    }

}
