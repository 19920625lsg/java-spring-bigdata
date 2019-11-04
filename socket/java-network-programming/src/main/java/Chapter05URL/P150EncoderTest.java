/***********************************************************
 * @Description : URL编码测试
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/10/10 23:39
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter05URL;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class P150EncoderTest {
    public static void main(String[] args) {
        try {
            // 空格会被替换成加号
            System.out.println(URLEncoder.encode("This string has spaces", "UTF-8"));
            // 字母、数字、下划线、连字符、点号和星号*不会被转换
            System.out.println(URLEncoder.encode("This*string*has*asterisks", "UTF-8"));
            System.out.println(URLEncoder.encode("This%string*has*percent%signs", "UTF-8"));
            // 其他的字符会被进行%转义
            System.out.println(URLEncoder.encode("This+string+has+pluses", "UTF-8"));
            System.out.println(URLEncoder.encode("This/string/has/slashes", "UTF-8"));
            System.out.println(URLEncoder.encode("This\"string\"has\"quote\"marks", "UTF-8"));
            System.out.println(URLEncoder.encode("This:string:has:colons", "UTF-8"));
            System.out.println(URLEncoder.encode("This~string~has~tildes", "UTF-8"));
            System.out.println(URLEncoder.encode("This(string)has(parentheses)", "UTF-8"));
            System.out.println(URLEncoder.encode("This.string.has.periods", "UTF-8"));
            System.out.println(URLEncoder.encode("This=string=has=equal=signs", "UTF-8"));
            System.out.println(URLEncoder.encode("This&string&has&ampersands", "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
/**
 * 输出如下：
 * <p>
 * This+string+has+spaces
 * This*string*has*asterisks
 * This%25string*has*percent%25signs
 * This%2Bstring%2Bhas%2Bpluses
 * This%2Fstring%2Fhas%2Fslashes
 * This%22string%22has%22quote%22marks
 * This%3Astring%3Ahas%3Acolons
 * This%7Estring%7Ehas%7Etildes
 * This%28string%29has%28parentheses%29
 * This.string.has.periods
 * This%3Dstring%3Dhas%3Dequal%3Dsigns
 * This%26string%26has%26ampersands
 */
