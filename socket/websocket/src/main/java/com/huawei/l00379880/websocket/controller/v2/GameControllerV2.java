/***********************************************************
 * @Description : 游戏通知功能的Controller。
 *                MessageMapping类似RequestMapping
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/8/8 22:53
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.websocket.controller.v2;

import com.huawei.l00379880.websocket.model.InMsg;
import com.huawei.l00379880.websocket.service.MyWebSocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class GameControllerV2 {

    @Autowired
    MyWebSocketService ws;

    @MessageMapping("/v2/chat")
    void getMsg(InMsg inMsg) {
        // 打印下日志信息
        log.info(inMsg.getContent());
        ws.sendTopicMsg("/topic/game_rank", inMsg);
    }
}
