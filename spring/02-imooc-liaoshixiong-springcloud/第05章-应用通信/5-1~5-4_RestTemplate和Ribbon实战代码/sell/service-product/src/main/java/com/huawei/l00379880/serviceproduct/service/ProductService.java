package com.huawei.l00379880.serviceproduct.service;

import com.huawei.l00379880.serviceproduct.dataobject.ProductInfo;

import java.util.List;


public interface ProductService {

    /**
     * 查询所有在架商品列表
     */
    List<ProductInfo> findUpAll();
}
