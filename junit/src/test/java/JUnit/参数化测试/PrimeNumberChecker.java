/***********************************************************
 * @Description : 
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/4/9 23:46
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package JUnit.参数化测试;

public class PrimeNumberChecker {
    public Boolean validate(final Integer parimeNumber) {
        for (int i = 2; i < (parimeNumber / 2); i++) {
            if (parimeNumber % i == 0) {
                return false;
            }
        }
        return true;
    }
}

