/***********************************************************
 * @Description : 客户端
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/10/13 16:26
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter02Start;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        // 可以在这里指定IP和端口，也可以在下面的connect函数里指定IP和端口，BIO、NIO、AIO那个课是在Socket构造函数里指明的
        Socket socket = new Socket();
        // 设置读取的超时时间
        socket.setSoTimeout(3000);
        // 连接本地，端口2000，设置连接超时时间
        socket.connect(new InetSocketAddress(Inet4Address.getLocalHost(), 2000), 3000);
        System.out.println("已经发起服务端连接，并进入后续流程");
        System.out.println("客户端信息~" + socket.getLocalAddress() + ":" + socket.getLocalPort());
        System.out.println("服务端信息~" + socket.getInetAddress() + ":" + socket.getPort());
        try {
            // 发送数据
            todo(socket);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("客户端异常关闭~");
        }
        // 释放资源
        socket.close();
        System.out.println("客户端已退出~");
    }

    private static void todo(Socket client) throws IOException {
        // 获取键盘输入流
        InputStream in = System.in;
        BufferedReader input = new BufferedReader(new InputStreamReader(in));

        // 得到Socket输出流, 并链式转换为打印流
        OutputStream outputStream = client.getOutputStream();
        PrintStream socketPrintStream = new PrintStream(outputStream);

        // 得到socket输入流，并转换为BufferedReader
        InputStream inputStream = client.getInputStream();
        BufferedReader socketBufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        boolean flag = true;
        do {
            // 键盘读取一行
            String str = input.readLine();
            // 发送到服务器
            socketPrintStream.println(str);

            // 从服务器读取一行
            String echo = socketBufferedReader.readLine();
            if ("bye".equalsIgnoreCase(echo)) {
                // 用户想要退出
                flag = false;
            } else {
                // 不想退出就把内容打印到屏幕上
                System.out.println(echo);
            }
        } while (flag);

        // 退出环境时把资源关闭
        socketPrintStream.close();
        socketBufferedReader.close();
    }
}

/**
 * 已经发起服务端连接，并进入后续流程
 * 客户端信息~/192.168.43.78:60699
 * 服务端信息~liangshangdeAir/192.168.43.78:2000
 * haha
 * 回送：4
 * heihei
 * 回送：6
 * hengkeng
 * 回送：8
 * bye
 * 客户端已退出~
 */