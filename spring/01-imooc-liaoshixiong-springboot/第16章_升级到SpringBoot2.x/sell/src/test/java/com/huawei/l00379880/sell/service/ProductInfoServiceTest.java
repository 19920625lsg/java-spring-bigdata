package com.huawei.l00379880.sell.service;

import com.huawei.l00379880.sell.entity.ProductInfo;
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
 * @Description : 
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/5/2 14:20
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceTest {

    @Autowired
    ProductInfoService service;

    @Test
    public void findOne() {
        ProductInfo productInfo = service.findOne("123456");
        System.out.println(productInfo);
    }

    @Test
    public void findAll() {
        PageRequest request = PageRequest.of(0, 2);
        Page<ProductInfo> productInfoPage = service.findAll(request);
        System.out.println("一共有" + productInfoPage.getTotalElements() + "个元素");
        List<ProductInfo> productInfoList = productInfoPage.getContent();
        for (ProductInfo productInfo : productInfoList) {
            System.out.println(productInfo);
        }
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> productInfoList = service.findUpAll();
        for (ProductInfo productInfo : productInfoList) {
            System.out.println(productInfo);
        }
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("abcdef");
        productInfo.setProductName("烧饼");
        productInfo.setProductPrice(new BigDecimal(1.5));
        productInfo.setProductStock(189);
        productInfo.setProductDescription("烧饼难吃");
        productInfo.setProductIcon("http://xxx.png");
        productInfo.setProductStatus(0); // 0是上架
        productInfo.setCategoryType(4); // 设置类别

        ProductInfo result = service.save(productInfo);
        System.out.println(result);
    }

    @Test
    public void save2() {
        ProductInfo productInfo = service.findOne("abcdef");
        productInfo.setCategoryType(10); // 设置类别

        ProductInfo result = service.save(productInfo);
        System.out.println(result);
    }
}