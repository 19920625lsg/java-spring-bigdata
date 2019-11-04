/***********************************************************
 * @Description : 服务器端定时推送的接口,以JVM监控为例
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/8/11 15:15
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.websocket.controller.v4;

import com.huawei.l00379880.websocket.service.MyWebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class ServerScheduleController {
    @Autowired
    private MyWebSocketService ws;

    @MessageMapping("/v4/schedule/push")
    // 定时3s执行一次.定时执行地方法不能有
    @Scheduled(fixedRate = 3000)
    void sendServerInfo() {
        ws.sendServerInfo();
    }
}
