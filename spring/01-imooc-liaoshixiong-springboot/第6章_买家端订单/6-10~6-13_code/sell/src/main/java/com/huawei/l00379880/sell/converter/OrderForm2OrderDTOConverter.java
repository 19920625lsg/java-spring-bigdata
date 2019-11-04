/***********************************************************
 * @Description : 表单到后端DTO对象地转换
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019/5/3 19:32
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.sell.converter;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.l00379880.sell.dto.OrderDTO;
import com.huawei.l00379880.sell.entity.OrderDetail;
import com.huawei.l00379880.sell.enums.ResultEnum;
import com.huawei.l00379880.sell.exception.SellException;
import com.huawei.l00379880.sell.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderForm2OrderDTOConverter {
    public static OrderDTO convert(OrderForm orderForm) {
        OrderDTO orderDTO = new OrderDTO();
        // 因为OrderDTO和OrderForm的字段名都不同，所以不知能用BeanUtils
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        // 转换items
        List<OrderDetail> orderDetailList = new ArrayList<>();
        Gson gson = new Gson();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
        } catch (JsonSyntaxException e) {
            log.error("【对象转换】错误, string={}", orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
