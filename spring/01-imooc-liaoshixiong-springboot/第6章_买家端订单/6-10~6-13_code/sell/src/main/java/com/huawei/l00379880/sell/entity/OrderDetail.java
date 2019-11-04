/***********************************************************
 * @Description : 订单详情类
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019/5/3 00:12
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.sell.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
public class OrderDetail {
    /**
     * 订单主键id
     */
    @Id
    private String detailId;

    /**
     * 订单主表id
     */
    private String orderId;

    /**
     * 商品id
     */
    private String productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品价格
     */
    private BigDecimal productPrice;

    /**
     * 商品数量
     */
    private Integer productQuantity;

    /**
     * 商品图片
     */
    private String productIcon;

    public OrderDetail() {
    }
}
