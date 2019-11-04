package net.xdclass.xdvideo.controller;

import net.xdclass.xdvideo.config.WeChatConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

	@RequestMapping("test")
	public String test(){
		System.out.println("xdclass.net");

		return "hello xdclass.net777";
	}



	@Autowired
	private WeChatConfig weChatConfig;

	@RequestMapping("test_config")
	public String testConfig(){

		System.out.println(weChatConfig.getAppId());
		return "hello xdclass.net";
	}
	
}
