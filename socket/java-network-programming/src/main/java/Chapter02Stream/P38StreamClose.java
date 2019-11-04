/***********************************************************
 * @Description : 流关闭的两种方式(所有实现Closeable接口的类)
 *                1.在finally中关闭(JDK6及以前版本)
 *                2.在try的构造块中关闭(JDK7中开始引入)
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/9/25 07:46
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter02Stream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class P38StreamClose {

    public static String path = "/Users/liangshanguang/Program/Java/Socket/java-network-programming/src/main/java/Chapter02Stream/P35CharactersGenerate.java";

    /**
     * 在finally中释放资源
     */
    public static void closeInFinally() {
        OutputStream out = null;

        try {
            out = new FileOutputStream(path);
            // 处理流......
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    // 这里的异常就不要打印；额
                    // e.printStackTrace();
                }
            }
        }
    }

    /**
     * 在Try构造块中进行资源申请，JVM会进行自动释放
     */
    public static void closeInTryBlock() {
        try(OutputStream out = new FileOutputStream(path)) {
            // 处理流......
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
