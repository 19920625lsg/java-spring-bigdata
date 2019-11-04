/***********************************************************
 * @Description : 
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/9/29 23:27
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter05URL;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class P129OpenStream {
    public static void main(String[] args) {
        try {
            URL u = new URL("https://www.baidu.com/");
            InputStream in = u.openStream();
            int c;
            while ((c=in.read())!=-1){
                System.out.write(c);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
