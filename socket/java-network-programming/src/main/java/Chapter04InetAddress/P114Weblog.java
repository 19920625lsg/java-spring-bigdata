/***********************************************************
 * @Description : Web服务器日志文件
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/9/29 22:54
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter04InetAddress;

import java.io.*;

public class P114Weblog {
    public static void main(String[] args) {
        try (FileInputStream fin = new FileInputStream(args[0])){
            Reader in = new InputStreamReader(fin);
            BufferedReader bin = new BufferedReader(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
