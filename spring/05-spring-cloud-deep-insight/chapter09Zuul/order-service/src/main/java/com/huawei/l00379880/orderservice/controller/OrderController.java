/***********************************************************
 * @Description : 
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/11/25 12:00
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.orderservice.controller;

import com.huawei.l00379880.orderservice.service.OrderService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("api/v1/order")
@Api(tags = "Order Operation")
public class OrderController {

    private final OrderService orderService;

    private final StringRedisTemplate redisTemplate;

    @Autowired
    public OrderController(OrderService orderService, StringRedisTemplate redisTemplate) {
        this.orderService = orderService;
        this.redisTemplate = redisTemplate;
    }

    @ApiOperation("保存订单")
    @GetMapping("/save")
    @HystrixCommand(fallbackMethod = "saveFail")
    Object save(@RequestParam("user_id") int userId, @RequestParam("product_id") int productId, HttpServletRequest request) {
        String token = request.getHeader("token");
        String cookie = request.getHeader("cookie");
        System.out.println("token=" + token);
        // Zuul默认会过滤Header中的Cookie、Set-Cookie和Authorization等字段(不过滤Toekn)，
        // 所以需要自己在application.yml中配置下不过滤(zuul.sensitive-headers设置为空)
        System.out.println("cookie=" + cookie);
        Map<String, Object> msg = new HashMap<>(2);
        msg.put("code", 0);
        msg.put("data:", orderService.save(userId, productId));
        return msg;
    }

    /**
     * 错误处理方法必须和上面的接口保持完全一致.接口返回值用Object类型是为了方便错误结果的返回
     */
    private Object saveFail(int userId, int productId, HttpServletRequest request) {
        // 开启子线程处理告警发送，不影响正常的错误处理流程
        new Thread(() -> {
            // 监控告警,异步进行
            String saveOrderKey = "save-order";
            String sendValue = redisTemplate.opsForValue().get(saveOrderKey);
            // 调用者的IP，可以用来记录出问题的VM，用于运维者溯源
            String ip = request.getRemoteAddr();
            if (StringUtils.isBlank(sendValue)) {
                // 告警短信发送，有短信服务的话可以尝试下，此处不写了
                System.out.println("紧急短信，用户下单失败！请理解查找原因。异常VM:" + ip);
                // 设置缓存的有效期是20s
                redisTemplate.opsForValue().set(saveOrderKey, "save-order-fail", 20, TimeUnit.SECONDS);
            } else {
                System.out.println("已经发送过告警短信，20s内不重复发送短信！");
            }
        }).start();

        //异常消息返回
        Map<String, Object> msg = new HashMap<>(2);
        msg.put("code", -1);
        msg.put("error:", "抢购人数太多了，请稍后重试");
        return msg;
    }
}
