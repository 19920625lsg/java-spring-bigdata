/***********************************************************
 * @Description : 服务器端,监听客户端连接并通信
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/10/13 16:25
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter02Start;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        // 启动服务器，在本机起，所有不需要主机IP，只给个端口就行
        ServerSocket server = new ServerSocket(2000);

        System.out.println("服务器准备就绪~");
        System.out.println("服务器信息：" + server.getInetAddress() + ":" + server.getLocalPort());

        for (; ; ) {
            // 死循环等待客户端连接
            Socket client = server.accept();
            // 给客户端连接分配单独的线程
            ClientHandler clientHandler = new ClientHandler(client);
            // 启动当前连接过来的客户端的线程
            clientHandler.start();
        }
    }

    /**
     * 客户端连接的处理类，处理客户端的消息
     */
    private static class ClientHandler extends Thread {
        private Socket socket;
        private boolean flag = true;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            super.run();
            System.out.println("有新客户端连接啦~" + socket.getInetAddress() + ":" + socket.getPort());

            try {
                // 得到打印流，用于数据输出；服务器回送数据使用
                PrintStream socketOutput = new PrintStream(socket.getOutputStream());
                // 得到输入流，用于接收数据
                BufferedReader socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                do {
                    // 客户端拿到一条数据
                    String str = socketInput.readLine();
                    if ("bye".equalsIgnoreCase(str)) {
                        // 代表客户端要关闭了
                        flag = false;
                        // 数据返回给客户端
                        socketOutput.println("bye");
                    } else {
                        // 打印到屏幕。并回送数据长度
                        System.out.println(str);
                        socketOutput.println("回送：" + str.length());
                    }
                } while (flag);
                socketInput.close();
                socketOutput.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("连接异常断开！");
            } finally {
                // 最终客户端连接的关闭
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("客户端已退出：" + socket.getInetAddress() + ":" + socket.getPort());
        }
    }
}

/**
 * 服务器准备就绪~
 * 服务器信息：0.0.0.0/0.0.0.0:2000
 * 有新客户端连接啦~/192.168.43.78:60699
 * haha
 * heihei
 * hengkeng
 * 客户端已退出：/192.168.43.78:60699
 */