/***********************************************************
 * @Description : 测试返回值
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/8/17 15:04
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package imooc.jvm.Chapter8JvmInCode;

public class TryFinally2 {
    public static void main(String[] args) {
        System.out.println(f1());
    }

    public static String f1() {
        String str = "hello";
        try {
            return str;
        } finally {
            // finally所以返回地是finally
            return "finally";
        }
    }
}

/**
 * 输出：
 * finally
 */