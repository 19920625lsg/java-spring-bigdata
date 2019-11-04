package com.huawei.l00379880.serviceorder.repository;

import com.huawei.l00379880.serviceorder.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
}
