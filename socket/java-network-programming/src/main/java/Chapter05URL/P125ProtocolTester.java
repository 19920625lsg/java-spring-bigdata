/***********************************************************
 * @Description : 查看当前机器支持哪些协议
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/9/29 23:09
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter05URL;

import java.net.MalformedURLException;
import java.net.URL;

public class P125ProtocolTester {
    private static void testProtocol(String url) {
        try {
            URL u = new URL(url);
            System.out.println(u.getProtocol() + " is supported");
        } catch (MalformedURLException e) {
            String protocol = url.substring(0, url.indexOf(':'));
            System.out.println(protocol + " is not supported");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // http:超文本传输协议
        testProtocol("http://www.adc.org");
        // 安全的http
        testProtocol("https://www.amazon.com");
        // 文件传输协议
        testProtocol("ftp://ibiblio.org/pub/languages/java/javafaq");
        // 简单邮件传输协议
        testProtocol("mailto://elharo@ibiblio.org");
        // telnet协议
        testProtocol("telnet://dibner.poly.edu");
        // 本地文件访问
        testProtocol("file://etc/passwd");
        // gopher
        testProtocol("gopher.anc.org.za");
        // ldap:轻量级目录访问协议
        testProtocol("ldap://ldap.itd.umich.edu");
        // NFS，网络文件系统
        testProtocol("nfs://utopia.poly.edu");
        // JDBC的定制协议
        testProtocol("jdbc:mysql://luna.ibiblio.org:3306/NEWS");
        // rmi，远程方法调用的定制协议
        testProtocol("rmi://ibiblio.org/RenderEngine");


    }
}
