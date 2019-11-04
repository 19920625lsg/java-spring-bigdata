/***********************************************************
 * @Description : 当product-service出现问题时的处理逻辑
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/11/25 18:28
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.orderservice.service.impl;

import com.huawei.l00379880.orderservice.entity.Product;
import com.huawei.l00379880.orderservice.service.ProductFeignClient;
import org.springframework.stereotype.Component;

/**
 * 针对商品服务的错误降级处理(如果请求不到ProductService会怎么处理的逻辑)
 */
@Component
public class ProductFallBack implements ProductFeignClient {
    @Override
    public Product findById(int id) {
        System.out.println("feign调用product-service异常");
        return null;
    }
}
