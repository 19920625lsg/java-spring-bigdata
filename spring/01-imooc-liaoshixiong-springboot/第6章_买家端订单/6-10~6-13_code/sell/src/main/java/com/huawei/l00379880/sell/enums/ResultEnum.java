package com.huawei.l00379880.sell.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    // 下面是本项目用到的所有错误码
    PARAM_ERR(1, "参数不正确"),
    PRODUCT_NOT_EXIST(10, "商品不存在"),
    PRODUCT_STOCK_ERR(11, "库存异常"),
    ORDER_MASTER_NOE_EXIST(12, "订单主表不存在"),
    ORDER_DETAIL_NOE_EXIST(13, "订单详情不存在"),
    ORDER_STATUS_ERR(14, "订单状态异常"),
    ORDER_UPDATE_ERR(15, "订单更新异常"),
    ORDER_DETAIL_EMPTY(16, "订单详情为空"),
    ORDER_PAY_STATUS_ERR(17, "订单支付状态异常"),
    CART_EMPTY(18, "购物车为空"),
    ORDER_OWNER_ERR(19, "该订单不属于当前请求者"),
    ORDER_NOT_EXIST(20, "订单不存在");

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String message;
}
