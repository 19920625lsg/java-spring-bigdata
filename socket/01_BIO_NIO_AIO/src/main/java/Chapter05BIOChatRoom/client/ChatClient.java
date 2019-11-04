/***********************************************************
 * @Description : 
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/9/17 23:01
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter05BIOChatRoom.client;

import java.io.*;
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
