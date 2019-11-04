/***********************************************************
 * @Description : 一般在实现类上写Service注解
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/11/24 20:35
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.productservice.service.impl;

import com.huawei.l00379880.productservice.entity.Product;
import com.huawei.l00379880.productservice.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    private static Map<Integer, Product> productMap = new HashMap<>();

    /**
     * 静态类，用于模拟数据库
     */
    static {
        Product p1 = new Product(1, "Mate20", 7999, 70);
        Product p2 = new Product(2, "P20", 6999, 60);
        Product p3 = new Product(3, "Magic2", 5999, 50);
        Product p4 = new Product(4, "Honor10", 4999, 40);
        Product p5 = new Product(5, "Nova3", 3799, 30);
        Product p6 = new Product(6, "IphoneX", 2999, 20);
        Product p7 = new Product(7, "Iphone8", 1999, 10);
        productMap.put(p1.getId(), p1);
        productMap.put(p2.getId(), p2);
        productMap.put(p3.getId(), p3);
        productMap.put(p4.getId(), p4);
        productMap.put(p5.getId(), p5);
        productMap.put(p6.getId(), p6);
        productMap.put(p7.getId(), p7);
    }

    @Override
    public List<Product> listProduct() {
        Collection<Product> collection = productMap.values();
        return new ArrayList<>(collection);
    }

    @Override
    public Product findById(int id) {
        return productMap.get(id);
    }
}
