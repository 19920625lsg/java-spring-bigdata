/***********************************************************
 * @Description : 
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/9/17 23:02
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter05BIOChatRoom.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/***********************************************************
 * @note      : 用户输入处理类
 * @author    : l00379880 梁山广
 * @version   : V1.0 at 2019/9/15 12:00
 ***********************************************************/
public class UserInputHandler implements Runnable {
    private ChatClient chatClient;

    public UserInputHandler(ChatClient chatClient) {
        this.chatClient = chatClient;
    }


    @Override
    public void run() {
        try {
            // 等待用户输入信息
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String inputStr = consoleReader.readLine();
                // 向服务器发送消息
                chatClient.send(inputStr);
                // 判断是否该退出了
                if (chatClient.readyToQuit(inputStr)){
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
