/***********************************************************
 * @Description : JUnit断言，主要是AssertXXX函数
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/4/9 23:25
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package JUnit;

import org.junit.Assert;
import org.junit.Test;

public class AssertionTest {

    @Test
    public void test() {
        String obj1 = "junit";
        String obj2 = "junit";
        String obj3 = "test";
        String obj4 = "test";
        String obj5 = null;

        int var1 = 1;
        int var2 = 2;

        int[] array1 = {1, 2, 3};
        int[] array2 = {1, 2, 3};

        Assert.assertEquals(obj1, obj2);

        Assert.assertSame(obj3, obj4);
        Assert.assertNotSame(obj2, obj4);

        Assert.assertNotNull(obj1);
        Assert.assertNull(obj5);

        Assert.assertTrue(var1 < var2);
        Assert.assertFalse(var1 > var2);

        Assert.assertArrayEquals(array1, array2);

    }
}

