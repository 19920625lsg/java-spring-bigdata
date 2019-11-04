/***********************************************************
 * @Description : 连接URL并获取内容
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/10/10 07:34
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter05URL;

import java.io.IOException;
import java.net.URL;

public class P133ContentGetter {
    public static void main(String[] args) {
        String url = "http://www.oreilly.com";
        try {
            URL u = new URL(url);
            Object o = u.getContent();
            System.out.println(o.getClass().getName());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
/**
 * sun.net.www.protocol.http.HttpURLConnection$HttpInputStream
 */