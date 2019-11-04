/***********************************************************
 * @Description : 
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/9/25 23:28
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter02Stream;

import java.io.IOException;
import java.io.InputStream;

public class P41InputStreamTest {
    void inputTest(InputStream in) throws IOException {
        // 每次读取的字节数
        int bytesRead = 0;
        // 读缓存的大小
        int bytesToRead = 1024;
        byte[] input = new byte[bytesToRead];

        while (bytesRead < bytesToRead) { // 只要还没读到1024就一直读
            int result = in.read(input, bytesRead, bytesToRead);
            // 把result单独提出来是为了防止result被加到bytesRead中(当要读的内容小于1024个字节时)
            if (result == -1) {
                break;
            }
            bytesRead += result;
        }
    }

    public static void main(String[] args) {

    }
}
