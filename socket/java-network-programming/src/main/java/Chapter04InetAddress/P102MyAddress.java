/***********************************************************
 * @Description : getHostAddress()方法的使用：可以拿到点分十进制的ip地址。很常用
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/9/28 23:10
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter04InetAddress;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class P102MyAddress {
    public static void main(String[] args) {
        try {
            InetAddress me = InetAddress.getLocalHost();
            String dottedQuad = me.getHostAddress();
            System.out.println(dottedQuad);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 192.168.100.105
 */