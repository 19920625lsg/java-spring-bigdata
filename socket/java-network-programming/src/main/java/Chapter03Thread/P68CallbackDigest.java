/***********************************************************
 * @Description : 利用回调函数获取多线程执行结果
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/9/27 07:17
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter03Thread;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class P68CallbackDigest implements Runnable {
    private String filename;

    public P68CallbackDigest(String filename) {
        this.filename = filename;
    }


    @Override
    public void run() {
        try {
            FileInputStream in = new FileInputStream(filename);
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            DigestInputStream din = new DigestInputStream(in, sha);
            while (din.read() != -1) {
                // do nothing。不断read的过程中实际是把加密信息存到了sha中
            }
            din.close();
            byte[] digest = sha.digest();
            P69CallbackDigestUserInterface.recieveDigest(digest, filename);

        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
    }
}
