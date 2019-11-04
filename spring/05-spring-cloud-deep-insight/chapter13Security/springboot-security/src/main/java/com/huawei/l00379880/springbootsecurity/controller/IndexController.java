/***********************************************************
 * @Description : 对外接口
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/12/1 16:20
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.springbootsecurity.controller;

import com.huawei.l00379880.springbootsecurity.entity.User;
import com.huawei.l00379880.springbootsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @ResponseBody
    @RequestMapping("/personal_center")
    public void login(HttpServletRequest request) {
        System.out.println("登录成功");
    }

    @ResponseBody
    @PostMapping("/registry")
    public void registry(User user) {
        System.out.println("注册成功");
        userRepository.save(new User(user.getUsername(), passwordEncoder.encode(user.getPassword())));
    }

    /**
     * WebMvcConfig类等效内容
     */
    @RequestMapping("/sign_in")
    public String sign_in() {
        return "login";
    }

    @RequestMapping("/sign_up")
    public String sign_up() {
        return "registry";
    }
}
