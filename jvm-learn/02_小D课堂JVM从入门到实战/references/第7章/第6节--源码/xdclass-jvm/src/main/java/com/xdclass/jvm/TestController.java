package com.xdclass.jvm;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {


    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello world";
    }






}