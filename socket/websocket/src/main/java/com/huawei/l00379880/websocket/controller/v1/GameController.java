/***********************************************************
 * @Description : 游戏通知功能的Controller。
 *                MessageMapping类似RequestMapping
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/8/8 22:53
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.websocket.controller.v1;

import com.huawei.l00379880.websocket.model.InMsg;
import com.huawei.l00379880.websocket.model.OutMsg;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GameController {

    @MessageMapping("/v1/chat")
    @SendTo("/topic/game_chat")
    OutMsg getMsg(InMsg inMsg) {
        return new OutMsg(inMsg.getContent());
    }
}
