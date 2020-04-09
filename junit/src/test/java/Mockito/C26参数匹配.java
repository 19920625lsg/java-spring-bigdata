/***********************************************************
 * @Description : C26参数匹配
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/4/10 0:39
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Mockito;

import org.junit.Test;
import org.mockito.ArgumentMatcher;

import java.util.Comparator;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class C26参数匹配 {
    @Test
    public void withArguments() {
        Comparable comparable = mock(Comparable.class);
        // 预设根据不同的参数返回不同的结果
        when(comparable.compareTo("Test")).thenReturn(1);
        when(comparable.compareTo("Omg")).thenReturn(2);
        assertEquals(1, comparable.compareTo("Test"));
        assertEquals(2, comparable.compareTo("Omg"));
        // 对于没有预设的情况会返回默认值
        assertEquals(0, comparable.compareTo("Not stub"));
    }

    @Test
    public void withUnspecifiedArguments() {
        List list = mock(List.class);
        // 匹配任意参数
        when(list.get(anyInt())).thenReturn(1);
        when(list.contains(argThat(new IsValid()))).thenReturn(true);
    }

    private class IsValid extends ArgumentMatcher<List> {

        @Override
        public boolean matches(Object o) {
            return (int) o == 1 || (int) o == 2;
        }
    }

    /**
     * 注意：如果你使用了参数匹配，那么所有的参数都必须通过matchers来匹配，如下代码：
     */
    @Test
    public void allArgumentsProvidedByMatchers(){
        Comparator comparator = mock(Comparator.class);
        comparator.compare("nihao", "hello");
        // 如果你使用了参数匹配，那么所有的参数都必须通过matchers来匹配
        verify(comparator).compare(anyString(), eq("hello"));
        // 下面的为无效的参数匹配使用
        // verify(comparator).compare(anyString(), "hello");
    }
}
