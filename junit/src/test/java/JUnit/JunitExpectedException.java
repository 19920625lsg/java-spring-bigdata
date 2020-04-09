/***********************************************************
 * @Description : 预期异常测试
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/4/9 23:45
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package JUnit;

import org.junit.Test;

public class JunitExpectedException {
    @Test(expected = ArithmeticException.class)
    public void testCase3() {
        System.out.println("in test case 3");
        int a = 0;
        int b = 1 / a;
    }
}
/**
 * in test case 3
 */
