/***********************************************************
 * @Description : JStack定位死循环和死锁问题
 * centos安装JDK，带JVM调试工具： yum install -y java-1.8.0-openjdk java-1.8.0-openjdk-devel
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/8/13 22:31
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package imooc.jvm.Chapter2BasicCmdTool;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static imooc.jvm.Chapter2BasicCmdTool.JStackEndlessLoop.getPartneridsFromJson;

@RestController
@RequestMapping("/jstack")
public class JStackController {

    /**
     * [死循环导致CPU负载高](https://blog.csdn.net/goldenfish1919/article/details/8755378)
     */
    @GetMapping("/endless_loop")
    public List<Long> endlessLoop() {
        String data = "{\"data\":[{\"partnerid\":]";
        return getPartneridsFromJson(data);
    }

    private Object lock1 = new Object();
    private Object lock2 = new Object();

    /**
     * 死锁
     */
    @RequestMapping("/deadlock")
    public String deadlock() {
        new Thread(() -> {
            synchronized (lock1) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
                synchronized (lock2) {
                    System.out.println("Thread1 over");
                }
            }
        }).start();
        new Thread(() -> {
            synchronized (lock2) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
                synchronized (lock1) {
                    System.out.println("Thread2 over");
                }
            }
        }).start();
        return "deadlock";
    }

}
