/***********************************************************
 * @Description : 默认InetAddress.toString()返回"主机名/ip"的字符串
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/9/28 22:59
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter04InetAddress;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class P98MyAddress {
    public static void main(String[] args) {
        InetAddress address;
        try {
            address = InetAddress.getLocalHost();
            System.out.println(address);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
/**
 * liangshanguangdeMacBook-Air.local/192.168.100.105
 */
