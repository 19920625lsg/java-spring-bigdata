/***********************************************************
 * @Description : 
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/3/4 22:39
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 第04章_运算符;

public class Underscores {
    public static void main(String[] args) {
        double d = 341_435_936.445_667;
        System.out.println(d);
        int bin = 0b0010_1111_1010_1111_1010_1111;
        System.out.println(bin);
        // Java 用 %n 实现的可以忽略平台间差异而生成适当的换行符
        System.out.printf("%x%n", bin);
        long hex = 0x6f_e9_b7_aa;
        System.out.printf("%x%n", hex);
    }
}
