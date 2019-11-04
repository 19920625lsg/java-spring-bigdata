package com.huawei.l00379880.sell.service;

import com.huawei.l00379880.sell.entity.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/***********************************************************
 * @Description : 测试类目接口服务类
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/5/2 13:07
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceTest {

    @Autowired
    ProductCategoryService productCategoryService;

    @Test
    public void findOne() {
        ProductCategory productCategory = productCategoryService.findOne(2);
        System.out.println(productCategory);
    }

    @Test
    public void findAll() {
        List<ProductCategory> categoryList = productCategoryService.findAll();
        for (ProductCategory category : categoryList) {
            System.out.println(category);
        }
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> categoryList = productCategoryService.findByCategoryTypeIn(Arrays.asList(2, 3, 5));
        for (ProductCategory category : categoryList) {
            System.out.println(category);
        }
    }

    @Test
    public void save() {
        ProductCategory productCategory = productCategoryService.findOne(2);
        productCategory.setCategoryType(100);
        productCategoryService.save(productCategory);
    }
}