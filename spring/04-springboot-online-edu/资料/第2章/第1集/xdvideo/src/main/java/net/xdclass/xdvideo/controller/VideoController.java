package net.xdclass.xdvideo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VideoController {

	@RequestMapping("test")
	public String test(){
		return "hello xdclass.net";
	}
	
	
	
}
