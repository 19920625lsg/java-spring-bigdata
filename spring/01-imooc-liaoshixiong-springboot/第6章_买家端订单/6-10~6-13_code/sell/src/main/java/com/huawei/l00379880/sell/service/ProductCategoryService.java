/***********************************************************
 * @Description : 商品类目服务
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019/5/2 12:57
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.sell.service;

import com.huawei.l00379880.sell.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    /**
     * 根据类目id查询指定类目
     */
    ProductCategory findOne(Integer categoryId);

    /**
     * 查询得到所有类目
     */
    List<ProductCategory> findAll();

    /**
     * 查找所有含类目表type的的类目集合
     *
     * @param categoryTypeList 类目type的集合
     * @return 含有categoryTypeList中的任何一个Type的ProductCategory集合
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    /**
     * 保存/更新类目
     */
    ProductCategory save(ProductCategory category);
}
