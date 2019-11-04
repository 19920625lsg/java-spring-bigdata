package com.huawei.l00379880.userservice.web;

import com.huawei.l00379880.userservice.dto.UserLoginDTO;
import com.huawei.l00379880.userservice.entity.User;
import com.huawei.l00379880.userservice.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fangzhipeng on 2017/6/1.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @PostMapping("/register")
    public User postUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        //参数判断，省略
        return userDetailsService.insertUser(username, password);
    }

    @PostMapping("/login")
    public UserLoginDTO login(@RequestParam("username") String username, @RequestParam("password") String password) {
        //参数判断，省略
        return userDetailsService.login(username, password);
    }
}
