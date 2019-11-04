/***********************************************************
 * @Description : 字符生成器.定义了一个翻出ASCII文本的服务器
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/9/25 07:29
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter02Stream;

import java.io.IOException;
import java.io.OutputStream;

public class P35CharactersGenerate {
    public static void gen(OutputStream out) throws IOException {
        int firstPrintableCharacter = 33;
        int numberOfPrintableCharacters = 94;
        int numberOfCharactersPerline = 72;

        int start = firstPrintableCharacter;
        while (true) {
            for (int i = start; i < (start + numberOfCharactersPerline); i++) {
                out.write((i - firstPrintableCharacter) % numberOfPrintableCharacters + firstPrintableCharacter);
            }
            out.write('\r');
            out.write('\n');
            start = ((start + 1) - firstPrintableCharacter) % numberOfPrintableCharacters + firstPrintableCharacter;
        }
    }

    public static void main(String[] args) {

    }
}
