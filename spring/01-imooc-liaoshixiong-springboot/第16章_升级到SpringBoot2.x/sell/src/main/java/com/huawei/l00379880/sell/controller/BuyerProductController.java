/***********************************************************
 * @Description : 买家端商品REST接口
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019/5/2 18:08
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.sell.controller;

import com.huawei.l00379880.sell.entity.ProductCategory;
import com.huawei.l00379880.sell.entity.ProductInfo;
import com.huawei.l00379880.sell.service.ProductCategoryService;
import com.huawei.l00379880.sell.service.ProductInfoService;
import com.huawei.l00379880.sell.utils.ResultVOUtil;
import com.huawei.l00379880.sell.vo.ProductInfoVO;
import com.huawei.l00379880.sell.vo.ProductVO;
import com.huawei.l00379880.sell.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    ProductInfoService productService;

    @Autowired
    ProductCategoryService categoryService;

    /**
     * 获取商品列表
     * <p>
     * 参见链接： https://github.com/19920625lsg/spring-springboot-springcloud-learn/blob/master/01-imooc-liaoshixiong-springboot/%E7%AC%AC5%E7%AB%A0_%E4%B9%B0%E5%AE%B6%E7%AB%AF%E5%95%86%E5%93%81/REST%E6%8E%A5%E5%8F%A3%E6%96%87%E6%A1%A3.md#%E5%95%86%E5%93%81%E5%88%97%E8%A1%A8
     */
    @GetMapping("/list")
    public ResultVO list() {

        // 1.查询所有的上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        // 2.查询类目(用JPA的in方法一次查询多个)
        List<Integer> categoryTypeList = new ArrayList<>();
        for (ProductInfo productInfo : productInfoList) {
            categoryTypeList.add(productInfo.getCategoryType());
        }
        List<ProductCategory> categoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        // 3.商品拼装
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory category : categoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(category.getCategoryName());
            productVO.setCategoryType(category.getCategoryType());

            // 查询指定类目下的所有product
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(category.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    // 复制同名属性
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        // 回填到最终的Result中
        return ResultVOUtil.success(productVOList);
    }

}
