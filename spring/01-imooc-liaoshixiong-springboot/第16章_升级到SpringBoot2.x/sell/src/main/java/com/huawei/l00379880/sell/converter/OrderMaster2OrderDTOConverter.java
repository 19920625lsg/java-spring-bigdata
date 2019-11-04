/***********************************************************
 * @Description : 转换器
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019/5/3 16:46
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.sell.converter;

import com.huawei.l00379880.sell.dto.OrderDTO;
import com.huawei.l00379880.sell.entity.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderMaster2OrderDTOConverter {
    private static OrderDTO convert(OrderMaster orderMaster) {
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList) {
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (OrderMaster orderMaster : orderMasterList) {
            orderDTOList.add(convert(orderMaster));
        }
        return orderDTOList;
    }
}
