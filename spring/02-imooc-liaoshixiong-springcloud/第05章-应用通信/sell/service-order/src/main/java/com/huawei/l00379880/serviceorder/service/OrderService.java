package com.huawei.l00379880.serviceorder.service;

import com.huawei.l00379880.serviceorder.dto.OrderDTO;

public interface OrderService {

    /**
     * 创建订单
     * @param orderDTO
     * @return
     */
    OrderDTO create(OrderDTO orderDTO);
}
