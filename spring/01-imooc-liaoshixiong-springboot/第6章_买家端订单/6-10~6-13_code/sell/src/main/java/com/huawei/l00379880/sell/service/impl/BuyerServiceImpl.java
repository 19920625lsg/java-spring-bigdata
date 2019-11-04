/***********************************************************
 * @Description : 查询订单
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019/5/4 00:24
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.sell.service.impl;

import com.huawei.l00379880.sell.dto.OrderDTO;
import com.huawei.l00379880.sell.enums.ResultEnum;
import com.huawei.l00379880.sell.exception.SellException;
import com.huawei.l00379880.sell.service.BuyerService;
import com.huawei.l00379880.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid, orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);
        if (orderDTO == null) {
            log.error("【取消订单】查不到该订单");
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancel(orderDTO);
    }

    private OrderDTO checkOrderOwner(String openid, String orderId) {
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null) {
            return null;
        }
        // 判断是否是自己的订单
        if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)) {
            // 非本人请求
            log.error("【查询订单】请求者openid和订单openid不一致");
            throw new SellException(ResultEnum.ORDER_OWNER_ERR);
        }
        return orderDTO;
    }
}
