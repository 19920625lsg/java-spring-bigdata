/***********************************************************
 * @Description : 从配置文件中注入属性
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/11/21 21:08
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.section3.controller;

import com.huawei.l00379880.section3.entity.Me;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableConfigurationProperties({Me.class})
public class MeController {
    @Value("${me.name}")
    private String name;
    @Value("${me.age}")
    private int age;

    private final Me me;

    @Autowired
    public MeController(Me me) {
        this.me = me;
    }

    @GetMapping(value = "/me")
    public String me() {
        return name + ":" + age;
    }

    @GetMapping(value = "/me2")
    public Me me2() {
        return me;
    }
}
