/***********************************************************
 * @Description : JUnit执行过程
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/4/9 23:40
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package JUnit;

import org.junit.*;

public class JunitTest {

    @BeforeClass
    public static void beforeClass() {
        System.out.println("in before class");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("in after class");
    }

    @Before
    public void before() {
        System.out.println("in before");
    }

    @After
    public void after() {
        System.out.println("in after");
    }

    @Test
    public void testCase1() {
        System.out.println("in test case 1");
    }

    @Test
    public void testCase2() {
        System.out.println("in test case 2");
    }
}
/**
 * in before class
 * in before
 * in test case 1
 * in after
 * in before
 * in test case 2
 * in after
 * in after class
 */
