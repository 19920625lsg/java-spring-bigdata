/***********************************************************
 * @Description : 验证Token的接口
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/12/5 00:37
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.authservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping(value = "/current")
    public Principal getUser(Principal principal) {
        // 验证接口
        return principal;
    }


}
