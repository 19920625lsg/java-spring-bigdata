/***********************************************************
 * @Description : Socket连接事件的监听器
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/8/9 23:52
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.websocket.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;

@Component
public class ConnectEventListener implements ApplicationListener<SessionConnectEvent> {


    @Override
    public void onApplicationEvent(SessionConnectEvent event) {
        // 获取消息响应器
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        SimpMessageType msgType = headerAccessor.getCommand().getMessageType();
        System.out.println("【SessionConnectEvent 监听器事件 类型】" + msgType);
    }
}
