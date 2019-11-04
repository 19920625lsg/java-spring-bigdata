/***********************************************************
 * @Description : 
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/11/25 12:02
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.orderservice.service.impl;

import com.huawei.l00379880.orderservice.entity.Order;
import com.huawei.l00379880.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private final RestTemplate restTemplate;

    @Autowired
    public OrderServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Order save(int userId, int productId) {
        //
        Map<String, Object> productMap = restTemplate.getForObject("http://product-service/api/v1/product/" + productId, Map.class);
        // 获取商品详情
        Order order = new Order();
        order.setCreateTime(new Date());
        order.setUserId(userId);
        order.setTradeNo(UUID.randomUUID().toString());
        order.setProductName(productMap.get("name").toString());
        order.setPrice(Integer.parseInt(productMap.get("price").toString()));
        return order;
    }
}
