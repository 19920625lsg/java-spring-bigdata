package com.huawei.l00379880.sell.enums;

import lombok.Getter;

/**
 * 商品状态
 */
@Getter
public enum ProductStatus {
    /**
     * 在售
     */
    UP(0, "在售"),
    /**
     * 下架
     */
    DOWN(1, "下架");
    private Integer code;
    private String message;

    ProductStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
