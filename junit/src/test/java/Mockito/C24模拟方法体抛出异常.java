/***********************************************************
 * @Description : 
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/4/10 0:26
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Mockito;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
public class C24模拟方法体抛出异常 {
    @Test(expected = RuntimeException.class)
    public void doThrowWhen(){
        List list = mock(List.class);
        doThrow(new RuntimeException()).when(list).add(1);
        list.add(1);
    }
}
