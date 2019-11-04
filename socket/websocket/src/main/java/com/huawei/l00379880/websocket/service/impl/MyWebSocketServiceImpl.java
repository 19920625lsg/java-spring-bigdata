/***********************************************************
 * @Description : 
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/8/9 00:21
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.websocket.service.impl;

import com.huawei.l00379880.websocket.model.InMsg;
import com.huawei.l00379880.websocket.model.OutMsg;
import com.huawei.l00379880.websocket.service.MyWebSocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MyWebSocketServiceImpl implements MyWebSocketService {
    @Autowired
    private SimpMessagingTemplate template;

    @Override
    public void sendTopicMsg(String destination, InMsg inMsg) {
        for (int i = 0; i < 20; i++) {
            template.convertAndSend(destination, new OutMsg(inMsg.getContent() + i));
        }
    }

    @Override
    public void sendChatMsg(InMsg msg) {
        template.convertAndSend("/chat/single/" + msg.getTo(), new OutMsg(msg.getFrom() + " 发送： " + msg.getContent()));
    }

    @Override
    public void sendServerInfo() {
        // 处理器个数
        int processors = Runtime.getRuntime().availableProcessors();
        // 可用内存
        Long freeMem = Runtime.getRuntime().freeMemory();
        // 最大内存
        Long maxMem = Runtime.getRuntime().maxMemory();
        String msgStr = "处理器个数：" + processors + ", 可用内存：" + freeMem + ", 最大内存：" + maxMem;
        // log.info(msgStr);
        template.convertAndSend("/topic/server_info", new OutMsg(msgStr));
    }
}
