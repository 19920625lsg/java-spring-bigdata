/***********************************************************
 * @Description : 订单主表操作类
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019/5/3 00:18
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.sell.repository;

import com.huawei.l00379880.sell.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
    /**
     * 根据买家的openId查询订单，并进行分页
     */
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
