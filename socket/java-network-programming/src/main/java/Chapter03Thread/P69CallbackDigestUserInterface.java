/***********************************************************
 * @Description : 
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/9/27 07:23
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter03Thread;

import javax.xml.bind.DatatypeConverter;

public class P69CallbackDigestUserInterface {
    public static void recieveDigest(byte[] digest, String filename) {
        StringBuilder sb = new StringBuilder(filename);
        sb.append(": ");
        sb.append(DatatypeConverter.printHexBinary(digest));
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        for (String filename : args) {
            // 计算摘要
            P68CallbackDigest cb = new P68CallbackDigest(filename);
            Thread t = new Thread(cb);
            t.start();
        }
    }
}
