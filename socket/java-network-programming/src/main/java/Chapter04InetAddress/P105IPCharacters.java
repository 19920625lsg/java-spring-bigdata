/***********************************************************
 * @Description : 测试IP地址的性质的10个函数
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/9/29 07:25
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter04InetAddress;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class P105IPCharacters {
    public static void main(String[] args) {
        try {
            // 命令行参数输入自己的IP即可
            InetAddress address = InetAddress.getByName(args[0]);
            if (address.isAnyLocalAddress()) {
                System.out.println(address + "是个通配符地址");
            }
            if (address.isLoopbackAddress()) {
                System.out.println(address + "是个回送地址");
            }

            // 本地地址的几种情况
            if (address.isLinkLocalAddress()) {
                System.out.println(address + "是个IPv6本地链接地址");
            } else if (address.isSiteLocalAddress()) {
                System.out.println(address + "是个IPv6本地网站地址");
            } else {
                System.out.println(address + "是个全球地址");
            }

            // 广播地址的几种情况
            if (address.isMulticastAddress()) {
                if (address.isMCGlobal()) {
                    System.out.println(address + "是个全球范围组播地址");
                } else if (address.isMCOrgLocal()) {
                    System.out.println(address + "是个组织范围组播地址");
                } else if (address.isMCSiteLocal()) {
                    System.out.println(address + "是个网站范围组播地址");
                } else if (address.isMCLinkLocal()) {
                    System.out.println(address + "是个子网范围组播地址");
                } else if (address.isMCNodeLocal()) {
                    System.out.println(address + "是个本地接口范围组播地址");
                } else {
                    System.out.println(address + "是个未知的组播地址类型");
                }
            } else {
                System.out.println(address + "是个非组播地址");
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
