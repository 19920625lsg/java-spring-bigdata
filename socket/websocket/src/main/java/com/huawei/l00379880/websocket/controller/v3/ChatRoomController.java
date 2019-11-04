/***********************************************************
 * @Description : 聊天室的接口
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/8/10 00:26
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.websocket.controller.v3;

import com.huawei.l00379880.websocket.model.InMsg;
import com.huawei.l00379880.websocket.service.MyWebSocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class ChatRoomController {

    @Autowired
    private MyWebSocketService ws;

    @MessageMapping("/v3/single/chat")
    void singleChat(InMsg msg) {
        log.info(msg.getContent());
        ws.sendChatMsg(msg);
    }
}
