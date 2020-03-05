/***********************************************************
 * @Description : https://lingcoder.gitee.io/onjava8/#/book/04-Operators?id=方法调用中的别名现象
 * 当我们把对象传递给方法时，会发生别名现象
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/3/4 22:18
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 第04章_运算符;

class Letter {
    char c;
}

public class PassObject {
    static void f(Letter y) {
        y.c = 'z';
    }

    public static void main(String[] args) {
        Letter x = new Letter();
        x.c = 'a';
        System.out.println("1：x.c = " + x.c);
        f(x);
        System.out.println("2：x.c = " + x.c);
    }
}
