/***********************************************************
 * @Description : 页面访问接口
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/12/3 22:57
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping("/")
    public String root() {
        // 根路径默认重定向到首页
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index() {
        // 首页
        return "index";
    }

    @RequestMapping("/user/index")
    public String userIndex() {
        // 用户信息首页
        return "user/index";
    }

    @RequestMapping("/login")
    public String login() {
        // 登录页
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        // 返回给前端，告知出异常了
        model.addAttribute("loginError", true);
        // 出错时返回登录页
        return "login";
    }

    @GetMapping("/401")
    public String accessDenied() {
        // 没有权限返回401
        return "401";
    }
}
