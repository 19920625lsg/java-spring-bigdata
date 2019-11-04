/***********************************************************
 * @Description : 测试RestTemplate通信
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-05-06 08:05
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.serviceorder.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class RestTemplateTestController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/getProductMsg1")
    public String getProductMsg() {
        // 1.第一种方式(直接使用restTemplate, url写死)
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("http://localhost:8080/msg", String.class);


        log.info("response={}", response);
        return response;
    }

    @GetMapping("/getProductMsg2")
    public String getProductMsg2() {

        // 2. 第二种方式(利用loadBalancerClient通过应用名获取url, 然后再使用restTemplate)
        RestTemplate restTemplate = new RestTemplate();
        ServiceInstance serviceInstance = loadBalancerClient.choose("SERVICE-PRODUCT");
        String url = String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort()) + "/msg";
        String response = restTemplate.getForObject(url, String.class);

        log.info("response={}", response);
        return response;
    }

    @GetMapping("/getProductMsg3")
    public String getProductMsg3() {

        //3. 第三种方式(利用@LoadBalanced注解的RestTemplateConfig类里的restTemplate的Bean, 可在restTemplate里使用应用名字)
        String response = restTemplate.getForObject("http://SERVICE-PRODUCT/msg", String.class);

        log.info("response={}", response);
        return response;
    }

}