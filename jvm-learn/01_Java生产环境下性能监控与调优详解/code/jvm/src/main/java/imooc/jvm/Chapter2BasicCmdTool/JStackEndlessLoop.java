/***********************************************************
 * @Description : JStack进入死循环的例子
 * 来自 [死循环导致CPU负载高](https://blog.csdn.net/goldenfish1919/article/details/8755378)
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/8/13 23:04
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package imooc.jvm.Chapter2BasicCmdTool;

import java.util.ArrayList;
import java.util.List;


public class JStackEndlessLoop {
    public static List<Long> getPartneridsFromJson(String data) {
        //{\"data\":[{\"partnerid\":982,\"count\":\"10000\",\"cityid\":\"11\"},{\"partnerid\":983,\"count\":\"10000\",\"cityid\":\"11\"},{\"partnerid\":984,\"count\":\"10000\",\"cityid\":\"11\"}]}
        //上面是正常的数据
        List<Long> list = new ArrayList<Long>(2);
        if (data == null || data.length() <= 0) {
            return list;
        }
        int datapos = data.indexOf("data");
        if (datapos < 0) {
            return list;
        }
        int leftBracket = data.indexOf("[", datapos);
        int rightBracket = data.indexOf("]", datapos);
        if (leftBracket < 0 || rightBracket < 0) {
            return list;
        }
        String partners = data.substring(leftBracket + 1, rightBracket);
        if (partners == null || partners.length() <= 0) {
            return list;
        }
        while (partners != null && partners.length() > 0) {
            int idpos = partners.indexOf("partnerid");
            if (idpos < 0) {
                break;
            }
            int colonpos = partners.indexOf(":", idpos);
            int commapos = partners.indexOf(",", idpos);
            if (colonpos < 0 || commapos < 0) {
                //partners = partners.substring(idpos+"partnerid".length());//1
                continue;
            }
            String pid = partners.substring(colonpos + 1, commapos);
            if (pid == null || pid.length() <= 0) {
                //partners = partners.substring(idpos+"partnerid".length());//2
                continue;
            }
            try {
                list.add(Long.parseLong(pid));
            } catch (Exception e) {
                //do nothing
            }
            partners = partners.substring(commapos);
        }
        return list;
    }
}
