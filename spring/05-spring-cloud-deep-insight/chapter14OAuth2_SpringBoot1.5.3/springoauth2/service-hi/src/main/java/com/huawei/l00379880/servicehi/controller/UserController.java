/***********************************************************
 * @Description : 验证Token的接口
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/12/5 00:37
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.servicehi.controller;

import com.huawei.l00379880.servicehi.entity.User;
import com.huawei.l00379880.servicehi.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/registry",method = RequestMethod.POST)
    public User createUser(@RequestParam("username") String username
            , @RequestParam("password") String password) {
        return userService.create(username,password);
    }

}
