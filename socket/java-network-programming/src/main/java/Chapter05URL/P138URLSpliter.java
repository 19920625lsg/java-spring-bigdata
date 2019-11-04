/***********************************************************
 * @Description : URL解析
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/10/10 07:56
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter05URL;

import java.net.MalformedURLException;
import java.net.URL;

public class P138URLSpliter {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            try {
                URL u = new URL(args[i]);
                System.out.println("The URL is " + u);
                System.out.println("The scheme is " + u.getProtocol());
                System.out.println("The user info is " + u.getUserInfo());

                String host = u.getHost();
                if (host != null) {
                    int atSign = host.indexOf('@');
                    if (atSign != -1) {
                        // 如果找到@的位置
                        host = host.substring(atSign + 1);
                        System.out.println("The host is " + host);
                    } else {
                        System.out.println("The host is null.");
                    }
                }
                System.out.println("The port is " + u.getPort());
                System.out.println("The path is " + u.getPath());
                System.out.println("The ref is " + u.getRef());
                System.out.println("The query string is " + u.getQuery());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            System.out.println();
        }
    }
}
/**
 * ftp://mp3:mp3@138.247.121.61:21000/c%2a/ http://www.oreilly.com
 */
