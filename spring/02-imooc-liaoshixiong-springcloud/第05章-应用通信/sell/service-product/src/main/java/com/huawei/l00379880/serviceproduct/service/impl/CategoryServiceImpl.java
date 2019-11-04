package com.huawei.l00379880.serviceproduct.service.impl;

import com.huawei.l00379880.serviceproduct.dataobject.ProductCategory;
import com.huawei.l00379880.serviceproduct.repository.ProductCategoryRepository;
import com.huawei.l00379880.serviceproduct.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategoryRepository.findByCategoryTypeIn(categoryTypeList);
    }
}
