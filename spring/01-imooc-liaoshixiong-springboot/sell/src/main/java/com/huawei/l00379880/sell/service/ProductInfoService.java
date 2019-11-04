/***********************************************************
 * @Description : 商品信息接口
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019/5/2 14:17
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.sell.service;

import com.huawei.l00379880.sell.dto.CartDTO;
import com.huawei.l00379880.sell.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductInfoService {
    /**
     * 查询指定id的商品
     */
    ProductInfo findOne(String productId);

    /**
     * 获取所有商品
     */
    Page<ProductInfo> findAll(Pageable pageable);

    /**
     * 获取所有商品状态为在售的商品
     */
    List<ProductInfo> findUpAll();

    /**
     * 保存商品
     */
    ProductInfo save(ProductInfo productInfo);

    /**
     * 加库存
     */
    void increaseStock(List<CartDTO> cartDTOList);

    /**
     * 减库存
     */
    void decreaseStock(List<CartDTO> cartDTOList);
}
