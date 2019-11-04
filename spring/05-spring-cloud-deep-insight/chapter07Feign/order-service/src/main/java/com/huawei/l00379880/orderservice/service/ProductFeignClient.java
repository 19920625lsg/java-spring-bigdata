/***********************************************************
 * @Description : 声明式调用
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/11/25 13:16
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.orderservice.service;

import com.huawei.l00379880.orderservice.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service")
public interface ProductFeignClient {

    @GetMapping("/api/v1/product/{id}")
    Product findById(@PathVariable("id") int id);
}
