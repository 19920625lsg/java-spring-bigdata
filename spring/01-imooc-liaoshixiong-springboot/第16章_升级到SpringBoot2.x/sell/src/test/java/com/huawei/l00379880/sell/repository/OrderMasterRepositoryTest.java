package com.huawei.l00379880.sell.repository;

import com.huawei.l00379880.sell.entity.OrderMaster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/***********************************************************
 * @Description : 服务主主表类测试
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/5/3 10:33
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    private final String OPENID = "111222333444";

    @Autowired
    OrderMasterRepository repository;

    @Test
    public void findOne() {
    }

    @Test
    public void findAll() {
    }

    /**
     * 新增数据的操作
     */
    @Test
    public void save() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("abcdef");
        orderMaster.setBuyerName("王蕊");
        orderMaster.setBuyerPhone("17712345678");
        orderMaster.setBuyerAddress("清华");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setOrderAmount(new BigDecimal(90.1));
        OrderMaster result = repository.save(orderMaster);
        System.out.println(result);
    }

    /**
     * 更新操作
     */
    @Test
    public void save2() {
        OrderMaster orderMaster = repository.findById("123456").orElse(null);
        orderMaster.setBuyerAddress("北京");
        OrderMaster result = repository.save(orderMaster);
        System.out.println(result);
    }

    @Test
    public void findByBuyerOpenid() {
        PageRequest pageRequest = PageRequest.of(0, 2);
        Page<OrderMaster> orderMasterPage = repository.findByBuyerOpenid(OPENID, pageRequest);
        System.out.println("本页有" + orderMasterPage.getTotalElements() + "条数据");
        List<OrderMaster> orderMasterList = orderMasterPage.getContent();
        for (OrderMaster orderMaster : orderMasterList) {
            System.out.println(orderMaster);
        }
    }
}