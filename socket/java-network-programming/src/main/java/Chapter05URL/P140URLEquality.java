/***********************************************************
 * @Description : 判断两个URL是否相等，相同主机、端口和路径上的相同资源
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/10/10 23:02
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter05URL;

import java.net.MalformedURLException;
import java.net.URL;

public class P140URLEquality {
    public static void main(String[] args) {
        try {
            URL www = new URL("http://www.ibiblio.org/");
            URL ibiblio = new URL("http://ibiblio.org/");
            if (www.equals(ibiblio)) {
                System.out.println("两个URL相等");
            } else {
                System.out.println("两个URL相等");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
