/***********************************************************
 * @Description : 基于多线程的消息搜索者
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/10/15 07:46
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter03UDP.Section6Thread;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class UDPSearcherThread {
    private static final int LISTEN_PORT = 30000;

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("UDPSearcherThread Started!");
        Listener listener = listen();
        sendBroadcast();

        // 读取任意键盘信息后可以退出
        System.in.read();
        List<Device> devices = listener.getDevicesAndClose();
        for (Device device : devices) {
            System.out.println("Device:" + device.toString());
        }
        System.out.println("UDP Searcher Finished.");
    }

    private static Listener listen() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Listener listener = new Listener(LISTEN_PORT, countDownLatch);
        listener.start();
        // 等待启动完成
        countDownLatch.await();
        return listener;
    }

    private static void sendBroadcast() throws IOException {
        System.out.println("UDPSearcherThread sendBroadcast started!");

        // 作为搜索方，无需指定端口，让系统直接分配
        DatagramSocket ds = new DatagramSocket();

        // 构建请求数据
        String requestData = MessageCreator.buildWithPort(LISTEN_PORT);
        // 构建回送消息的实体
        byte[] requestDataBytes = requestData.getBytes();
        // 直接根据发送者构建一份回送信息
        DatagramPacket requestPacket = new DatagramPacket(
                requestDataBytes,
                requestDataBytes.length
        );
        // 获取广播地址
        requestPacket.setAddress(InetAddress.getByName("255.255.255.255"));
        requestPacket.setPort(20000);
        // 把回送信息发送回去
        ds.send(requestPacket);
        ds.close();

        // 完成，关闭下资源
        System.out.println("UDPSearcherThread sendBroadcast Finished");
    }

    private static class Device {
        final int port;
        final String p;
        final String sn;

        public Device(int port, String p, String sn) {
            this.port = port;
            this.p = p;
            this.sn = sn;
        }

        @Override
        public String toString() {
            return "Device{" +
                    "port=" + port +
                    ", p='" + p + '\'' +
                    ", sn='" + sn + '\'' +
                    '}';
        }
    }

    private static class Listener extends Thread {

        private final int listenPort;
        private final CountDownLatch countDownLatch;
        private final List<Device> devices = new ArrayList<>();

        private boolean done = false;
        private DatagramSocket ds = null;


        public Listener(int listenPort, CountDownLatch countDownLatch) {
            this.listenPort = listenPort;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            super.run();
            // 通知已启动
            countDownLatch.countDown();
            try {
                ds = new DatagramSocket(listenPort);
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
                    System.out.println("UDPSearcherThread receive from ip:" + ip + "\tport: " + port + "\tdata:" + data);
                    String sn = MessageCreator.parseSn(data);
                    if (sn != null) {
                        Device device = new Device(port, ip, sn);
                        devices.add(device);
                    }
                }
            } catch (Exception e) {

            } finally {
                close();
            }
            System.out.println("UDPSearcherThread listener finished!");
        }

        private void close() {
            if (ds != null) {
                ds.close();
                ds = null;
            }
        }

        List<Device> getDevicesAndClose() {
            done = true;
            close();
            return devices;
        }
    }
}
