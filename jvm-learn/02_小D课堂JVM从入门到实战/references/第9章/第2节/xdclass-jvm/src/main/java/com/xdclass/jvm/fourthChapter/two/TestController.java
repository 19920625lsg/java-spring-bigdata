package com.xdclass.jvm.fourthChapter.two;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {


    private static final int SIZE = 1024*1024*100;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() throws InterruptedException {
        byte[] bytes = new byte[SIZE];
        Thread.sleep(100000);
        return "hello world";
    }




}