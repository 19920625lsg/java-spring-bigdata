/***********************************************************
 * @Description : 
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/9/17 22:56
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter04BIO.Basic;

import java.io.*;
import java.net.Socket;

/***********************************************************
 * @note      :
 * @author    : l00379880 梁山广
 * @version   : V1.0 at 2019/9/12 8:35
 ***********************************************************/
public class Client {
    public static void main(String[] args) {
        final String DEFAULT_SERVER_HOST = "127.0.0.1";
        final int DEFAULT_SERVER_PORT = 8888;
        // 解决消息中文乱码的问题
        final String CHARSET = "UTF-8";
        Socket socket = null;
        try {
            System.out.println("我是客户端......");
            while (true) {
                // 创建socket
                socket = new Socket(DEFAULT_SERVER_HOST, DEFAULT_SERVER_PORT);
                // 等待客户端连接
                String clientIp = socket.getInetAddress().getHostAddress();
                int clientPort = socket.getPort();

                // 创建IO流
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), CHARSET));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), CHARSET));
                // 等待用户输入信息
                BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
                String inputStr = consoleReader.readLine();
                // 把用户输入地信息发送给服务器
                writer.write(inputStr + "\n");
                writer.flush();
                // 读取服务器返回的消息
                String msg = reader.readLine();
                System.out.println("服务端发过来地消息【" + clientIp + ":" + clientPort + "】" + msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
