/***********************************************************
 * @Description : 测试地址是IPv4还是IPv6
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/9/28 23:15
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter04InetAddress;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class P103AddressTest {
    public static int getIPType(InetAddress ia) {
        byte[] address = ia.getAddress();
        if (address.length == 4) {
            return 4;
        } else if (address.length == 16) {
            return 6;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) throws UnknownHostException {
        InetAddress me = InetAddress.getLocalHost();
        System.out.println("IPv" + getIPType(me));
    }
}
