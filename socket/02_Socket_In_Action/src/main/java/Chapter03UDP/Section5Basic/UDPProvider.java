/***********************************************************
 * @Description : UDP提供者，用于提供服务
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/10/14 23:08
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter03UDP.Section5Basic;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPProvider {
    public static void main(String[] args) throws IOException {
        System.out.println("UDP Provider started!");

        // 作为接收者，指定一个端口进行接收
        DatagramSocket ds = new DatagramSocket(20000);

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
        System.out.println("UDP Provider receive from ip:" + ip + "\tport: " + port + "\tdata:" + data);

        // 构建回送数据
        String responseData = "Receive data with len:" + dataLen;
        // 构建回送消息的实体
        byte[] responseDataBytes = responseData.getBytes();
        // 直接根据发送者构建一份回送信息
        DatagramPacket responsePacket = new DatagramPacket(
                responseDataBytes,
                responseDataBytes.length,
                reveivePack.getAddress(),
                reveivePack.getPort()
        );
        // 把回送信息发送回去
        ds.send(responsePacket);

        // 完成，关闭下资源
        System.out.println("UDP Provider Finished");
        ds.close();
    }
}
/**
 * UDP Provider started!
 * UDP Provider receive from ip:192.168.100.105	port: 62193	data:Hello UDP World
 * UDP Provider Finished
 */