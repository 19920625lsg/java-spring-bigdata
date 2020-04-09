/***********************************************************
 * @Description : 使用built-in runner：MockitoJUnitRunner
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/4/10 0:33
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Mockito.C25使用注解来快速模拟;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MockitoExample3 {
    @Mock
    private List mockList;

    @Test
    public void shortHand(){
        mockList.add(1);
        // 验证是否add(1)了
        verify(mockList).add(1);
    }
}
