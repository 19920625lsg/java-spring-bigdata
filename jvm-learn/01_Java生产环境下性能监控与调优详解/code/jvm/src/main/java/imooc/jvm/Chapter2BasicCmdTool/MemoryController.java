package imooc.jvm.Chapter2BasicCmdTool;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/***********************************************************
 * @note      : jmap+MAT分析内存溢出
 * 情况1：堆溢出
 * 情况2：Metaspace溢出
 * 堆和MetaSpace的示意图见 [JVM内存结构](https://img1.sycdn.imooc.com/szimg/5d525b2b0001f94719201080.jpg)
 * @author    : l00379880 梁山广
 * @version   : V1.0 at 2019/8/13 14:25
 ***********************************************************/
@RestController
public class MemoryController {
    private List<User> userList = new ArrayList<>();

    /**
     * 堆溢出示例
     * 需要先设置最大内存和最小内存，在Run Configurations-->Configurations-->VM Options 添加"-Xmx32M -Xms32M"即可
     * 访问：http://localhost:9999/heap
     * 很快就会出现如下错误： Exception in thread "http-nio-9999-exec-1" java.lang.OutOfMemoryError: GC overhead limit exceeded
     * 出现堆内存溢出了，因为类的实例对象是存放在堆里面的，当不断new新的User对象时，一旦超过Xmx配置的堆内存上限，就会产生堆内存溢出现象了
     * 要熟悉堆区和栈区的区别，参考文章 JVM中堆和栈的区别：https://www.cnblogs.com/Jashinck/p/10480776.html
     */
    @GetMapping("/heap")
    public void heap() {
        // 堆溢出
        int i = 0;
        while (true) {
            userList.add(new User(i++, UUID.randomUUID().toString()));
        }
    }

    private List<Class<?>> classList = new ArrayList<>();

    /**
     * 非堆区溢出，这里以MetaSpace区为例，MetaSpace里面存储地是类、Field等元数据信息
     * 在Run Configurations-->Configurations-->VM Options 添加"-XX:MetaspaceSize=32M -XX:MaxMetaspaceSize=32M" 即可(JDK8)
     * 很快就会报错：java.lang.OutOfMemoryError: GC overhead limit exceeded
     */
    @GetMapping("/nonheap")
    public void nonheap() {
        // 非堆溢出，插入1000w个随机的类(Metaspace生成)
        classList.addAll(Metaspace.createClasses());
    }

}
