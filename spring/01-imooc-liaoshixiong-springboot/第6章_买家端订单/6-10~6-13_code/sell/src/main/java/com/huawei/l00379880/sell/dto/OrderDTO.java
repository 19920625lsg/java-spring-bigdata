/***********************************************************
 * @Description : 订单主表DTO
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019/5/3 11:49
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.sell.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.huawei.l00379880.sell.entity.OrderDetail;
import com.huawei.l00379880.sell.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {
    /**
     * 订单id
     */
    private String orderId;

    /**
     * 买家名字
     */
    private String buyerName;

    /**
     * 买家手机号
     */
    private String buyerPhone;

    /**
     * 买家地址
     */
    private String buyerAddress;

    /**
     * 买家微信openid
     */
    private String buyerOpenid;

    /**
     * 订单金额
     */
    private BigDecimal orderAmount;

    /**
     * 订单状态
     */
    private Integer orderStatus;

    /**
     * 支付状态
     */
    private Integer payStatus;

    /**
     * 创建时间，自定义序列化类，好好掌握
     */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    /**
     * 订单主表下的商品表
     */
    private List<OrderDetail> orderDetailList;

    public OrderDTO() {
    }
}
