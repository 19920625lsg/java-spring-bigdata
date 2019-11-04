/***********************************************************
 * @Description : 
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/9/25 23:28
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter02Stream;

import java.io.IOException;
import java.io.InputStream;

public class P39InputStreamTest {
    void inputTest(InputStream in) throws IOException {
        byte[] input = new byte[10];
        for (int i = 0; i < input.length; i++) {
            int b = in.read();
            // 读函数返回-1表示读取到头了
            if (b == -1) {
                break;
            }
            input[i] = (byte) b;
        }
    }

    public static void main(String[] args) {

    }
}
