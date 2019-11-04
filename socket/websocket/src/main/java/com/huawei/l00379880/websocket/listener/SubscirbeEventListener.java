/***********************************************************
 * @Description : 
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/8/9 08:07
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.websocket.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

@Component
public class SubscirbeEventListener implements ApplicationListener<SessionSubscribeEvent> {
    /**
     * StompHeaderAccessor：简单消息传递协议中处理消息头的基类，通过这个类，可以获取消息类型，
     * 通过这个类可以获取消息类型(例如：发布订阅、建立连接、断开连接等)、会话id等
     *
     * @param event 监听事件
     */
    @Override
    public void onApplicationEvent(SessionSubscribeEvent event) {
        // 获取消息响应器
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        SimpMessageType msgType = headerAccessor.getCommand().getMessageType();
        System.out.println("【SessionSubscribeEvent监听器事件 类型】" + msgType);
    }
}
