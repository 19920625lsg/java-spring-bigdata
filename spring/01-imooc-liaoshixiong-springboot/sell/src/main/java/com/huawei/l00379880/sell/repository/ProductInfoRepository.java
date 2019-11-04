/***********************************************************
 * @Description : 商品信息的数据库操作Dao类
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019/5/2 14:03
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.sell.repository;

import com.huawei.l00379880.sell.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {
    List<ProductInfo> findByProductStatus(Integer status);
}
