/***********************************************************
 * @Description : 
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/3/5 22:38
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 第06章_初始化和清理;

public class SimpleEnumUse {
    public static void main(String[] args) {
        Spiciness howHot = Spiciness.MEDIUM;
        System.out.println(howHot);
        for (Spiciness s : Spiciness.values()) {
            System.out.println(s + ": " + s.ordinal());
        }
    }
}
