/***********************************************************
 * @Description : 购物车
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019/5/3 15:34
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.sell.dto;

import lombok.Data;

@Data
public class CartDTO {
    /**
     * 商品id
     */
    private String productId;
    /**
     * 数量
     */
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }

    public CartDTO() {
    }
}
