/***********************************************************
 * @Description : 字符生成器.定义了一个翻出ASCII文本的服务器
 * 优化：使用缓存并一次性全部写出
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/9/25 07:29
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter02Stream;

import java.io.IOException;
import java.io.OutputStream;

public class P36CharactersGenerateOptimize {
    public static void gen(OutputStream out) throws IOException {
        int firstPrintableCharacter = 33;
        int numberOfPrintableCharacters = 94;
        int numberOfCharactersPerline = 72;

        int start = firstPrintableCharacter;
        byte[] line = new byte[numberOfCharactersPerline + 2];
        while (true) {
            for (int i = start; i < (start + numberOfCharactersPerline); i++) {
                // 存储在缓存数组中
                line[i - start] = (byte) ((i - firstPrintableCharacter) % numberOfPrintableCharacters + firstPrintableCharacter);
            }
            line[72] = (byte) '\r';
            line[73] = (byte) '\n';
            out.write(line);
            start = ((start + 1) - firstPrintableCharacter) % numberOfPrintableCharacters + firstPrintableCharacter;
        }
    }

    public static void main(String[] args) {

    }
}
