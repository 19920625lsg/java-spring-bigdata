package com.huawei.l00379880.sell.repository;

import com.huawei.l00379880.sell.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

/***********************************************************
 * @Description : ProductCategory测试类
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/5/2 11:01
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository repository;

    /**
     * 查询
     */
    @Test
    public void findOneTest() {
        // 根据id查找数据
        ProductCategory productCategory = repository.findById(1).orElse(null);
        System.out.println(productCategory);
    }

    /**
     * 添加(挨个设置必须字段)
     */
    @Test
    public void addTest() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("女生最爱");
        productCategory.setCategoryType(3);
        repository.save(productCategory); // 自动生成id、create_time和update_time
    }

    /**
     * 添加(构造方法设置必须字段)
     */
    @Test
    // @Transactional 这个注解会在Test方法执行完毕后回滚所有操作
    public void addTest2() {
        // ProductCategory productCategory = new ProductCategory("男生最爱", 3); // categoryType不能为3，因为我们设置了这个字段唯一
        ProductCategory productCategory = new ProductCategory("男生最爱", 4); // categoryType不能为3，因为我们设置了这个字段唯一
        ProductCategory result = repository.save(productCategory); // 自动生成id、create_time和update_time
        Assert.assertNotNull(result); // 保存数据库成功会返回非空实例对象
        Assert.assertNotEquals(null, result); // 保存数据库成功会返回非空实例对象，和上面一句等效
    }

    /**
     * 更新
     */
    @Test
    public void updateTest() {
        ProductCategory productCategory = repository.findById(2).orElse(null); // 查找指定数据
        productCategory.setCategoryName("我最爱");
        repository.save(productCategory); // 会自动更新update_time的
    }

    /**
     * 查找符合条件的ProductCategory列表
     */
    @Test
    public void findByCategoryTypeInTest() {
        List<ProductCategory> categoryList = repository.findByCategoryTypeIn(Arrays.asList(2, 3, 5));
        for (ProductCategory category : categoryList) {
            System.out.println(category);
        }
    }
}