/***********************************************************
 * @Description : UDP提供者，用于提供服务,基于多线程的，可以复用
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/10/14 23:08
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter03UDP.Section6Thread;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.UUID;

public class UDPProviderThread {
    public static void main(String[] args) throws IOException {
        // 生成一份唯一标识
        String sn = UUID.randomUUID().toString();
        Provider provider = new Provider(sn);
        provider.start();

        // 读取任意字符退出
        System.in.read();
        provider.exit();
    }

    private static class Provider extends Thread {
        private final String sn;
        private boolean done = false;
        private DatagramSocket ds = null;

        public Provider(String sn) {
            super();
            this.sn = sn;
        }

        @Override
        public void run() {
            System.out.println("UDP Provider started!");
            try {
                // 作为接收者，指定一个端口进行接收
                ds = new DatagramSocket(20000);
                while (!done) {
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

                    // 解析端口号
                    int responsePort = MessageCreator.parsePort(data);
                    if (responsePort != -1) {
                        // 构建回送数据
                        String responseData = MessageCreator.buildWithSn(sn);
                        // 构建回送消息的实体
                        byte[] responseDataBytes = responseData.getBytes();
                        // 直接根据发送者构建一份回送信息
                        DatagramPacket responsePacket = new DatagramPacket(
                                responseDataBytes,
                                responseDataBytes.length,
                                reveivePack.getAddress(),
                                // 会送到客户端指定的接收端口号
                                responsePort
                        );
                        // 把回送信息发送回去
                        ds.send(responsePacket);
                    }
                }
            } catch (IOException e) {
                // just do nothing
                // e.printStackTrace();
            } finally {
                // 完成，关闭下资源
                System.out.println("UDP Provider Finished");
                close();
            }
        }

        private void close() {
            if (ds != null) {
                ds.close();
                ds = null;
            }
        }

        void exit() {
            done = true;
            close();
        }
    }
}


/**
 * UDP Provider started!
 * UDP Provider receive from ip:192.168.100.105	port: 62193	data:Hello UDP World
 * UDP Provider Finished
 */