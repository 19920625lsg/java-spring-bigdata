/***********************************************************
 * @Description : 对象序列化用于缓存是区分对象
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/11/24 20:30
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.productservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
    private static final long serialVersionUID = -3236623829188055083L;
    private int id;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 价格
     */
    private int price;
    /**
     * 库存
     */
    private int store;
}
