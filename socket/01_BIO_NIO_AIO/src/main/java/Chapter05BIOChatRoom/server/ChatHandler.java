/***********************************************************
 * @Description : 
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/9/17 23:01
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter05BIOChatRoom.server;

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
