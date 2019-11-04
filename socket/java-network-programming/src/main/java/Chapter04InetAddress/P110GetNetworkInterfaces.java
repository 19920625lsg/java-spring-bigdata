/***********************************************************
 * @Description : 列出本地主机上的所有网络端口
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/9/29 22:40
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter04InetAddress;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class P110GetNetworkInterfaces {
    public static void main(String[] args) throws SocketException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface ni = interfaces.nextElement();
            System.out.println(ni);
        }
    }
}
