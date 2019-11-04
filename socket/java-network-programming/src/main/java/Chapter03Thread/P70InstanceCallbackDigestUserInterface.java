/***********************************************************
 * @Description : 
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/9/27 07:37
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter03Thread;

import javax.xml.bind.DatatypeConverter;
import java.util.Arrays;

public class P70InstanceCallbackDigestUserInterface {
    private String filename;
    private byte[] digest;

    public P70InstanceCallbackDigestUserInterface(String filename) {
        this.filename = filename;
    }

    public void calculateDigest(){
        P69InstanceCallbackDigest cb = new P69InstanceCallbackDigest(filename, this);
        Thread t = new Thread(cb);
        t.start();
    }

    public void receiveDigest(byte[] digest) {
        this.digest = digest;
        System.out.println(this);
    }

    @Override
    public String toString() {
        String result = filename +": ";
        if (digest != null) {
            result += DatatypeConverter.printHexBinary(digest);
        }else {
            result += "digest not available";
        }
        return result;
    }

    public static void main(String[] args) {
        for (String filename : args) {
            // 计算摘要
            P70InstanceCallbackDigestUserInterface d = new P70InstanceCallbackDigestUserInterface(filename);
            d.calculateDigest();
        }
    }
}
