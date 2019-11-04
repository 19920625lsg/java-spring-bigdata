/***********************************************************
 * @Description : 产品的Controller
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/11/24 20:29
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.productservice.controller;

import com.huawei.l00379880.productservice.entity.Product;
import com.huawei.l00379880.productservice.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@Api(tags = "Product Operation")
@Slf4j
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Value("${server.port}")
    private String port;

    @GetMapping("/list")
    @ApiOperation("获取商品列表")
    List<Product> listProduct() {
        return productService.listProduct();
    }

    @GetMapping("/{id}")
    @ApiOperation("根据商品ID查询商品信息")
    Product findById(@PathVariable int id) {
        log.info("l00379880 enter product service findById");
        Product product = productService.findById(id);
        Product result = new Product();
        // 复制对象，防止引用传值影响原来的属性
        BeanUtils.copyProperties(product, result);
        result.setName(result.getName() + " from port : " + port);
        return result;
    }
}
