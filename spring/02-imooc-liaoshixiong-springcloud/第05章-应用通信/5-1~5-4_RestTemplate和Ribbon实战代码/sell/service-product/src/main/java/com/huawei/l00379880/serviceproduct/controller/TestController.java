/***********************************************************
 * @Description : 用于测试服务间通信的Controller
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-05-06 08:03
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.serviceproduct.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/msg")
    public String msg() {
        return "this is product's msg";
    }

}
