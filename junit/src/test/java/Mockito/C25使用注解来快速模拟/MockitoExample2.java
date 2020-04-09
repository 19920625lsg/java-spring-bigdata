/***********************************************************
 * @Description : 运行这个测试类你会发现报错了，mock的对象为NULL，为此我们必须在基类中添加初始化mock的代码
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/4/10 0:33
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Mockito.C25使用注解来快速模拟;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
public class MockitoExample2 {
    @Mock
    private List mockList;
    public MockitoExample2() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shortHand(){
        mockList.add(1);
        // 验证是否add(1)了
        verify(mockList).add(1);
    }
}
