package com.huawei.l00379880.serviceproduct.service.impl;

import com.huawei.l00379880.serviceproduct.dataobject.ProductInfo;
import com.huawei.l00379880.serviceproduct.enums.ProductStatusEnum;
import com.huawei.l00379880.serviceproduct.repository.ProductInfoRepository;
import com.huawei.l00379880.serviceproduct.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }
}
