package com.huawei.l00379880.sell.repository;

import com.huawei.l00379880.sell.entity.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/***********************************************************
 * @Description : 
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/5/3 11:01
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    OrderDetailRepository repository;

    @Test
    public void findOne() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void save() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("abcdefgh");
        orderDetail.setOrderId("111111111");
        orderDetail.setProductIcon("http://xxxxx.jpg");
        orderDetail.setProductId("111111112");
        orderDetail.setProductName("皮蛋粥");
        orderDetail.setProductPrice(new BigDecimal(1.2));
        orderDetail.setProductQuantity(2);

        OrderDetail result = repository.save(orderDetail);
        System.out.println(result);
    }

    @Test
    public void save2() {
        OrderDetail orderDetail = repository.findOne("123456789");
        orderDetail.setOrderId("222222222");

        OrderDetail result = repository.save(orderDetail);
        System.out.println(result);
    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> orderDetailList = repository.findByOrderId("111111111");
        for (OrderDetail orderDetail : orderDetailList) {
            System.out.println(orderDetail);
        }
    }
}