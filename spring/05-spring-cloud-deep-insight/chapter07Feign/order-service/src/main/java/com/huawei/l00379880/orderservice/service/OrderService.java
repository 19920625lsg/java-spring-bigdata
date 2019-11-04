/***********************************************************
 * @Description : 订单服务
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/11/25 12:01
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.orderservice.service;

import com.huawei.l00379880.orderservice.entity.Order;

public interface OrderService {
    /**
     * 下单接口
     *
     * @param userId    用户ID
     * @param productId 商品ID
     * @return 保存好的订单(含ID)
     */
    Order save(int userId, int productId);
}
