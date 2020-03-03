/***********************************************************
 * @Description : System的常见方法
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/3/4 0:59
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 第03章_万物皆对象;

public class ShowProperties {
    public static void main(String[] args) {
        System.getProperties().list(System.out);
        System.out.println(System.getProperty("user.name"));
        System.out.println(System.getProperty("java.library.path"));
    }
}
