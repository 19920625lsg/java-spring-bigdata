package com.huawei.l00379880.sell.enums;

import lombok.Getter;

/**
 * 支付状态的枚举
 */
@Getter
public enum PayStatusEnum {
    /**
     * 等待支付
     */
    WAIT(0, "等待支付"),
    /**
     * 支付成功
     */
    SUCCESS(1, "支付成功");

    private Integer code;
    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
