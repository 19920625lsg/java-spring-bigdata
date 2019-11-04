package com.huawei.l00379880.sell.enums;

import lombok.Getter;

/**
 * 订单状态的枚举
 */
@Getter
public enum OrderStatusEnum {
    /**
     * 新订单
     */
    NEW(0, "新订单"),
    /**
     * 订单完成
     */
    FINISHED(1, "完结"),
    /**
     * 订单取消
     */
    CANCEL(2, "已取消");

    private Integer code;
    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
