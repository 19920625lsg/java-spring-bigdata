/***********************************************************
 * @Description : 2.2 模拟我们所期望的结果
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/4/10 0:07
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Mockito;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class C22模拟我们所期望的结果 {
    @Test
    public void whenThenReturn() {
        // mock一个Iterator类
        Iterator iterator = mock(Iterator.class);
        // 预设当iterator调用next时第一次返回hello，第n次都返回world
        when(iterator.next()).thenReturn("hello").thenReturn("world");
        // 使用mock对象
        String result = iterator.next() + " " + iterator.next() + " " + iterator.next();
        // 验证结果
        Assert.assertEquals("hello world world", result);
    }
}
