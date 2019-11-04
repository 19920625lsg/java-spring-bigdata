/***********************************************************
 * @Description : 
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/11/21 21:52
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.section3.controller;

import com.huawei.l00379880.section3.entity.Her;
import com.huawei.l00379880.section3.entity.Me;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableConfigurationProperties({Her.class, Me.class})
public class HerController {
    private final Me me;
    private final Her her;

    @Autowired
    public HerController(Me me, Her her) {
        this.me = me;
        this.her = her;
    }

    @GetMapping("/us")
    private String us() {
        return me.getName() + "和" + her.getName();
    }
}
