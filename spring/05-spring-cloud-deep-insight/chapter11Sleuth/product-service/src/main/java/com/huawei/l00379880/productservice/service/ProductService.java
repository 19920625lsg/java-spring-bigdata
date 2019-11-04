/***********************************************************
 * @Description : 
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/11/24 20:33
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.productservice.service;

import com.huawei.l00379880.productservice.entity.Product;

import java.util.List;

public interface ProductService {
    /**
     * 获取所有的商品列表
     *
     * @return 所有商品对象的列表
     */
    List<Product> listProduct();

    /**
     * 根据ID查询对象
     *
     * @param id 商品id
     * @return 商品对象
     */
    Product findById(int id);
}
