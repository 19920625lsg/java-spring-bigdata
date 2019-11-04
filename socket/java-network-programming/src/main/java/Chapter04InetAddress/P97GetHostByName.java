/***********************************************************
 * @Description : 根据域名获取IP等信息
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/9/28 22:54
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter04InetAddress;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class P97GetHostByName {
    public static void main(String[] args) {
        try {
            // 根据域名获取
            InetAddress address1 = InetAddress.getByName("www.oreilly.com");
            System.out.println(address1);
            // 直接输出ip也是可以的
            InetAddress address2 = InetAddress.getByName("23.209.3.43");
            System.out.println(address2);
            // 获取域名后面所有的ip
            InetAddress[] addresses = InetAddress.getAllByName("www.baidu.com");
            for (InetAddress address : addresses) {
                System.out.println(address);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
