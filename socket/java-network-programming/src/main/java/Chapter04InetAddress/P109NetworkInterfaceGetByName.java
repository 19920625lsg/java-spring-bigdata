/***********************************************************
 * @Description : 
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/9/29 22:28
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter04InetAddress;

import java.net.NetworkInterface;
import java.net.SocketException;

public class P109NetworkInterfaceGetByName {
    public static void main(String[] args) {
        try {
            NetworkInterface ni = NetworkInterface.getByName("eh0");
            if (ni == null) {
                System.err.println("No such interface: eth0");
            }
        } catch (SocketException e) {
            System.err.println("Could not list sockets.");
        }
    }
}
