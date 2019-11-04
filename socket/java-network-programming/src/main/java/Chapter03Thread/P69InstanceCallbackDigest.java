/***********************************************************
 * @Description : 使用实例方法代替静态方法
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/9/27 07:35
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter03Thread;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class P69InstanceCallbackDigest implements Runnable {
    private String filename;
    private P70InstanceCallbackDigestUserInterface callback;

    public P69InstanceCallbackDigest(String filename, P70InstanceCallbackDigestUserInterface callback) {
        this.filename = filename;
        this.callback = callback;
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
            callback.receiveDigest(digest);

        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
    }
}
