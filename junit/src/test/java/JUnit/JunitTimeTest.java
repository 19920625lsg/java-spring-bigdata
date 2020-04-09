/***********************************************************
 * @Description : JUnit执行过程
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/4/9 23:40
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package JUnit;

import org.junit.*;

public class JunitTimeTest {

    @Test(timeout = 1000)
    public void testCase1() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("in test case 1");
    }
}
/**
 * org.junit.runners.model.TestTimedOutException: test timed out after 1000 milliseconds
 * ......更多异常信息......
 *
 */
