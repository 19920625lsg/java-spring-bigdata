package com.huawei.l00379880.serviceorder.service.impl;

import com.huawei.l00379880.serviceorder.dataobject.OrderMaster;
import com.huawei.l00379880.serviceorder.dto.OrderDTO;
import com.huawei.l00379880.serviceorder.enums.OrderStatusEnum;
import com.huawei.l00379880.serviceorder.enums.PayStatusEnum;
import com.huawei.l00379880.serviceorder.repository.OrderDetailRepository;
import com.huawei.l00379880.serviceorder.repository.OrderMasterRepository;
import com.huawei.l00379880.serviceorder.service.OrderService;
import com.huawei.l00379880.serviceorder.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {

       //TODO 查询商品信息(调用商品服务)
       //TODO 计算总价
       //TODO 扣库存(调用商品服务)

        //订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(KeyUtil.genUniqueKey());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(new BigDecimal(5));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
