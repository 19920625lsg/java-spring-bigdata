/***********************************************************
 * @Description : 压缩文件的线程类
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/9/28 22:32
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter03Thread;

import java.io.*;
import java.util.zip.GZIPOutputStream;

public class P92GZipRunnable implements Runnable {

    private final File input;

    public P92GZipRunnable(File input) {
        this.input = input;
    }

    @Override
    public void run() {
        // 不压缩已经压缩的文件
        if (!input.getName().endsWith(".gz")) {
            File output = new File(input.getParent(), input.getName() + ".gz");
            // 不覆盖已经存在的文件
            if (!output.exists()) {
                // try构造中放入输入流和输出流的初始化，就不用资源释放的问题了
                try (
                        InputStream in = new BufferedInputStream(new FileInputStream(input));
                        OutputStream out = new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream(output)));
                ) {
                    int b;
                    while ((b = in.read()) != -1) {
                        out.write(b);
                        out.flush();
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
