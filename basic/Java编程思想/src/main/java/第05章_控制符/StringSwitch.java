/***********************************************************
 * @Description : String也支持switch了
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/3/4 23:36
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 第05章_控制符;

public class StringSwitch {
    public static void main(String[] args) {
        String color = "red";
        switch (color){
            case "red":
                System.out.println("RED");
                break;
            case "green":
                System.out.println("GREEN");
                break;
            case "blue":
                System.out.println("BLUE");
            case "yellow":
                System.out.println("YELLOW");
                break;
            default:
                System.out.println("Unknown");
                break;
        }
    }
}
