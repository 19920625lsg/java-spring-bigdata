package com.huawei.l00379880.serviceproduct.service;

import com.huawei.l00379880.serviceproduct.dataobject.ProductCategory;

import java.util.List;


public interface CategoryService {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
