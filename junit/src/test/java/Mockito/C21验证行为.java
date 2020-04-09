/***********************************************************
 * @Description : 
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/4/10 0:02
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Mockito;

import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class C21验证行为 {
    @Test
    public void verifyBehavior() {
        // 模拟创建一个List对象
        List mock = mock(List.class);
        // 使用mock对象
        mock.add(1);
        mock.clear();
        // 验证add(1)和clear()行为是否发生
        verify(mock).add(1);
        verify(mock).clear();
    }
}
