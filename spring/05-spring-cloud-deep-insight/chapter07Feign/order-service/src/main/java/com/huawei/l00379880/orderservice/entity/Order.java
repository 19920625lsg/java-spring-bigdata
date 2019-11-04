/***********************************************************
 * @Description : 商品实体类
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/11/25 11:47
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.orderservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {

    private static final long serialVersionUID = 6659745200237508987L;
    /**
     * 订单编号
     */
    private int id;
    /**
     * 用户id
     */
    private int userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 交易流水号
     */
    private String tradeNo;
    /**
     * 价格
     */
    private int price;
    /**
     * 订单创建时间
     */
    private Date createTime;
}
