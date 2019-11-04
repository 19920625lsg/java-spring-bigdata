/***********************************************************
 * @Description : 
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/11/25 12:02
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.orderservice.service.impl;

import com.huawei.l00379880.orderservice.entity.Order;
import com.huawei.l00379880.orderservice.entity.Product;
import com.huawei.l00379880.orderservice.service.OrderService;
import com.huawei.l00379880.orderservice.service.ProductFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private final ProductFeignClient productFeignClient;

    @Autowired
    public OrderServiceImpl(ProductFeignClient productFeignClient) {
        this.productFeignClient = productFeignClient;
    }


    @Override
    public Order save(int userId, int productId) {
        Product product = productFeignClient.findById(productId);
        System.out.println(product);
        // 获取商品详情
        Order order = new Order();
        order.setCreateTime(new Date());
        order.setUserId(userId);
        order.setTradeNo(UUID.randomUUID().toString());
        order.setProductName(product.getName());
        order.setPrice(product.getPrice());
        return order;
    }
}
