/***********************************************************
 * @Description : Try-Finally返回值测试
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/8/17 14:56
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package imooc.jvm.Chapter8JvmInCode;

public class TryFinally {
    public static void main(String[] args) {
        System.out.println(f1());
    }

    public static String f1() {
        String str = "hello";
        try {

            return str;
        } finally {
            // 这里的赋值操作是无意义地，return返回的时候已经取了hello的值了
            str = "finally";
        }
    }
}

/**
 * 输出：
 * hello
 */
