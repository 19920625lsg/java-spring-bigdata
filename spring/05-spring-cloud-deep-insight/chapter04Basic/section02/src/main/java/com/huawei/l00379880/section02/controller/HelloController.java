/***********************************************************
 * @Description : 第一个SpringBoot的例子
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/11/21 20:44
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.section02.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
