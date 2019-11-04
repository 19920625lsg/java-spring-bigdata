/***********************************************************
 * @Description : 
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/10/14 23:09
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter03UDP.Section5Basic;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPSearcher {
    public static void main(String[] args) throws IOException {
        System.out.println("UDPSearcher started!");

        // 作为搜索方，无需指定端口，让系统直接分配
        DatagramSocket ds = new DatagramSocket();

        // 构建请求数据
        String requestData = "Hello UDP World";
        // 构建回送消息的实体
        byte[] requestDataBytes = requestData.getBytes();
        // 直接根据发送者构建一份回送信息
        DatagramPacket requestPacket = new DatagramPacket(
                requestDataBytes,
                requestDataBytes.length
        );
        requestPacket.setAddress(InetAddress.getLocalHost());
        requestPacket.setPort(20000);
        // 把回送信息发送回去
        ds.send(requestPacket);

        // 构建接收实体
        final byte[] buf = new byte[512];
        DatagramPacket reveivePack = new DatagramPacket(buf, buf.length);

        // 接收
        ds.receive(reveivePack);

        // 打印接收到的信息与发送者的信息
        // 发送者的IP地址
        String ip = reveivePack.getAddress().getHostAddress();
        int port = reveivePack.getPort();
        // 拿到数据大小
        int dataLen = reveivePack.getLength();
        String data = new String(reveivePack.getData(), 0, dataLen);
        System.out.println("UDPSearcher receive from ip:" + ip + "\tport: " + port + "\tdata:" + data);

        // 完成，关闭下资源
        System.out.println("UDPSearcher Finished");
        ds.close();
    }
}
/**
 * UDPSearcher started!
 * UDPSearcher receive from ip:192.168.100.105	port: 20000	data:Receive data with len:15
 * UDPSearcher Finished
 */
