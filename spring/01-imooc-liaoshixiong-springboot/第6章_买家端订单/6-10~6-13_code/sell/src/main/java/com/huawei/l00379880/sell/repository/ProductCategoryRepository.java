/***********************************************************
 * @Description : 操作类目表ProductCategory的数据库Dao类
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019/5/2 10:44
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.sell.repository;

import com.huawei.l00379880.sell.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    /**
     * 查找所有含类目表type的的类目集合
     *
     * @param categoryTypeList 类目type的集合
     * @return 含有categoryTypeList中的任何一个Type的ProductCategory集合
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
