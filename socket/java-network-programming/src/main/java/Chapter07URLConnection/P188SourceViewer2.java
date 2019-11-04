/***********************************************************
 * @Description : 读取服务器的数据,类似初步的爬虫了
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/10/13 11:19
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter07URLConnection;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class P188SourceViewer2 {
    public static void main(String[] args) {

        // 打开URLConnection进行读取
        try {
            URL url = new URL("http://www.huawei.com");
            URLConnection uc = url.openConnection();
            try (InputStream raw = uc.getInputStream()) {
                InputStream buffer = new BufferedInputStream(raw);
                // 将InputStream串链到一个Reader
                Reader reader = new InputStreamReader(buffer);
                int c;
                while ((c = reader.read()) != -1) {
                    System.out.print((char) c);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
/**
 * 输出如下：
 *
 * <html>
 * <head><title>301 Moved Permanently</title></head>
 * <body bgcolor="white">
 * <center><h1>301 Moved Permanently</h1></center>
 * <hr><center>nginx</center>
 * </body>
 * </html>
 */