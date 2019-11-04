package Chapter04BIO.Basic;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/***********************************************************
 * @note      :
 * @author    : l00379880 梁山广
 * @version   : V1.0 at 2019/9/11 20:16
 ***********************************************************/
public class Server {
    public static void main(String[] args) {
        final int DEFAULT_PORT = 8888;
        // 解决消息中文乱码的问题
        final String CHARSET = "UTF-8";
        ServerSocket serverSocket = null;
        try {
            System.out.println("我是服务端......");
            serverSocket = new ServerSocket(DEFAULT_PORT);
            System.out.println("启动服务器，监听端口：" + DEFAULT_PORT);
            while (true) {
                // 等待客户端连接
                Socket socket = serverSocket.accept();
                String clientIp = socket.getInetAddress().getHostAddress();
                int clientPort = socket.getPort();
                System.out.println("客户端【" + clientIp + ":" + clientPort + "】已连接");
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), CHARSET));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), CHARSET));
                // 读取客户端发来地消息
                String msg = reader.readLine();
                if (msg != null) {
                    System.out.println("客户端发过来的消息【" + clientIp + ":" + clientPort + "】" + msg);
                    // 回复客户端发送地消息
                    writer.write("服务器：" + msg + "\n");
                    writer.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
