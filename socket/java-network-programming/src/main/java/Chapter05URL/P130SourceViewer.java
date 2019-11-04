/***********************************************************
 * @Description : 下载一个Web页面的原始代码
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/9/29 23:34
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter05URL;

import java.io.*;
import java.net.URL;

public class P130SourceViewer {
    public static void main(String[] args) {
        InputStream in = null;
        try {
            // 打开URL进行读取
            URL u = new URL("https://www.oreilly.com");
            in = u.openStream();
            // 缓冲输入以提高性能
            in = new BufferedInputStream(in);
            Reader r = new InputStreamReader(in);
            // 将InputStream串链到一个Reader
            int c;
            while ((c = r.read()) != -1) {
                System.out.print((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    // 忽略
                }
            }
        }
    }
}
