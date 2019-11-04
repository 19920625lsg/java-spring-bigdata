/***********************************************************
 * @Description : URI的各部分
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/10/10 23:15
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter05URL;

import java.net.URI;
import java.net.URISyntaxException;

public class P146URISpliter {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            try {
                URI u = new URI(args[i]);
                System.out.println("The URI is " + u);
                if (u.isOpaque()) {
                    System.out.println("This is a opaque URI.");
                    System.out.println("协议是：" + u.getScheme());
                    System.out.println(u.getSchemeSpecificPart());
                    System.out.println(u.getFragment());
                } else {
                    System.out.println("这是个层级URI");
                    System.out.println("协议是：" + u.getScheme());
                }
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }
}
