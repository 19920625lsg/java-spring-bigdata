/***********************************************************
 * @Description : 买家服务
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019/5/4 00:22
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.sell.service;

import com.huawei.l00379880.sell.dto.OrderDTO;

public interface BuyerService {
    /**
     * 查询一个订单
     */
    OrderDTO findOrderOne(String openid, String orderId);

    /**
     * 取消订单
     */
    OrderDTO cancelOrder(String openid, String orderId);
}
