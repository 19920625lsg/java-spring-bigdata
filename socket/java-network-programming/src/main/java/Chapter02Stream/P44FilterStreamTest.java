/***********************************************************
 * @Description : 
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/9/26 07:21
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter02Stream;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class P44FilterStreamTest {
    public static void main(String[] args) throws FileNotFoundException {
        // 将过滤器流串链到一起，只用最后一个过滤器进行实际的读写
        DataOutputStream dout = new DataOutputStream(
                                new BufferedOutputStream(
                                new FileOutputStream("data.txt")
                )
        );
    }
}
