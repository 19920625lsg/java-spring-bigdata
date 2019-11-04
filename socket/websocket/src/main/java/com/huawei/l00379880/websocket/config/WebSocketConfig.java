/***********************************************************
 * @Description : WebSocket连接需要的配置
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/8/8 22:53
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 注册通信端点(基站)，发布或者订阅消息的时候需要连接此端点。withSockJS表示开启socketjs的支持
        registry.addEndpoint("/endpoint-websocket").setAllowedOrigins("*").withSockJS();
    }

    /**
     * 配置消息代理
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // enableSimpleBroker表示服务端推送给客户端的接口(Controller里的SendTo里的URL)的前缀。可以指定多个进行匹配
        registry.enableSimpleBroker("/topic", "/chat");
        // 在Controller的接口(MessageMapping里的URL)地址前加一个统一的前缀。
        registry.setApplicationDestinationPrefixes("/app");
    }
}
