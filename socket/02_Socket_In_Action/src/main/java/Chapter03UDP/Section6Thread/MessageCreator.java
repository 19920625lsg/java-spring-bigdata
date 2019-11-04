/***********************************************************
 * @Description : 消息构造
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/10/14 23:38
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter03UDP.Section6Thread;

public class MessageCreator {
    private static final String SN_HEADER = "收到暗号，我是(SN)";
    private static final String PORT_HEADER = "这是暗号，请回电端口";

    public static String buildWithPort(int port) {
        return PORT_HEADER + port;
    }

    public static int parsePort(String data) {
        if (data.startsWith(PORT_HEADER)) {
            return Integer.parseInt(data.substring(PORT_HEADER.length()));
        }
        return -1;
    }

    public static String buildWithSn(String sn) {
        return SN_HEADER + sn;
    }

    public static String parseSn(String data) {
        if (data.startsWith(SN_HEADER)) {
            return data.substring(SN_HEADER.length());
        }
        return null;
    }
}
