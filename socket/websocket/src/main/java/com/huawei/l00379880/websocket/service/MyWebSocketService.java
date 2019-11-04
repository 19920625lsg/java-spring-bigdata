/***********************************************************
 * @Description : 消息服务
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/8/9 00:18
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.websocket.service;

import com.huawei.l00379880.websocket.model.InMsg;

public interface MyWebSocketService {
    /**
     * V2更好的给客户端回消息的方式，SendTo的另一种实现
     *
     * @param destination SendTo中的URL
     * @param inMsg       要发送的消息
     */
    void sendTopicMsg(String destination, InMsg inMsg);

    /**
     * 发送聊天消息,指定人
     *
     * @param msg 聊天消息体
     */
    void sendChatMsg(InMsg msg);

    /**
     * 服务端向订阅者定时发送消息
     */
    void sendServerInfo();
}
