/***********************************************************
 * @Description : 与IP地址绑定的网络接口
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/9/29 22:33
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter04InetAddress;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class P110NetworkInterfaceGetByInetAddress {
    public static void main(String[] args) {
        try {
            InetAddress local = InetAddress.getByName("127.0.0.1");
            NetworkInterface ni = NetworkInterface.getByInetAddress(local);
            if (ni == null) {
                System.err.println("没有本地回环地址");
            }
        } catch (UnknownHostException | SocketException e) {
            e.printStackTrace();
        }
    }
}
