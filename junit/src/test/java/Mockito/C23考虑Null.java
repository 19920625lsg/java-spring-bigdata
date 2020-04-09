/***********************************************************
 * @Description : 在创建mock对象时，有的方法我们没有进行stubbing，
 * 所以调用时会放回Null这样在进行操作是很可能抛出NullPointerException。
 * 如果通过RETURNS_SMART_NULLS参数创建的mock对象在没有调用stubbed方法
 * 时会返回SmartNull。例如：返回类型是String，会返回"";是int，会返回0；
 * 是List，会返回空的List。另外，在控制台窗口中可以看到SmartNull的友好提示。
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/4/10 0:12
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Mockito;

import org.junit.Test;
import java.util.List;

import static org.mockito.Mockito.*;

public class C23考虑Null {
    @Test
    public void returnSmartNullsTest(){
        List mock = mock(List.class, RETURNS_SMART_NULLS);
        System.out.println(mock.get(0));

        // 使用RETURN_SMART_NULLS参数创建的mock对象，不会抛出NullPointerException异常，控制台显示地是“SmartNull returned by unstubbed get() method on mock”
        System.out.println(mock.toArray().length);
    }
}
/**
 * SmartNull returned by this unstubbed method call on a mock:
 * list.get(0);
 * 0
 */
