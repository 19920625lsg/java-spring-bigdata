/***********************************************************
 * @Description : 垃圾邮件检测
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/9/29 22:46
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter04InetAddress;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class P112SpamCheck {
    public static final String BLACK_HOLE = "sbl.spamhaus.org";

    private static boolean isSpammer(String arg) {
        try {
            InetAddress address = InetAddress.getByName(arg);
            byte[] quad = address.getAddress();
            String query = BLACK_HOLE;
            for (byte octet : quad) {
                int unsignedByte = octet < 0 ? octet + 256 : octet;
                query = unsignedByte + "." + query;
            }
            InetAddress.getByName(query);
            return true;
        } catch (UnknownHostException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        for (String arg : args) {
            if (isSpammer(arg)) {
                System.out.println(arg + "是个垃圾邮件发送者");
            }
        }
    }
}
