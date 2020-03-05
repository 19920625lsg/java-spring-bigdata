/***********************************************************
 * @Description : 构造函数调用
 * 1.只能通过 this 调用一次构造器。
 * 2.必须首先调用构造器，否则编译器会报错
 * 3.编译器不允许你在一个构造器之外的方法里调用构造器。
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/3/4 23:57
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 第06章_初始化和清理;

// housekeeping/Flower.java
// Calling constructors with "this"

public class Flower {
    int petalCount = 0;
    String s = "initial value";

    Flower(int petals) {
        petalCount = petals;
        System.out.println("Constructor w/ int arg only, petalCount = " + petalCount);
    }

    Flower(String ss) {
        System.out.println("Constructor w/ string arg only, s = " + ss);
        s = ss;
    }

    Flower(String s, int petals) {
        this(petals);
        //- this(s); // 1.只能通过 this 调用一次构造器
        this.s = s; // Another use of "this"
        System.out.println("String & int args");
    }

    Flower() {
        this("hi", 47);
        System.out.println("no-arg constructor");
    }

    void printPetalCount() {
        //- this(11); // 3.编译器不允许你在一个构造器之外的方法里调用构造器。
        System.out.println("petalCount = " + petalCount + " s = " + s);
    }

    public static void main(String[] args) {
        Flower x = new Flower();
        x.printPetalCount();
    }
}

