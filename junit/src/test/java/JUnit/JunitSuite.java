/***********************************************************
 * @Description : 把两个用例集成到一个测试套中
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/4/9 23:49
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package JUnit;

import JUnit.套件测试.JunitTest1;
import JUnit.套件测试.JunitTest2;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        /**
         * 此处类的配置顺序会影响执行顺序
         */
        JunitTest1.class,
        JunitTest2.class
})
public class JunitSuite {

}
/**
 * in JunitTest1
 * in JunitTest2
 */
