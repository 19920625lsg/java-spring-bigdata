package com.xdclass.jvm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
//	String a = "abc";
//	String b = "abc";
//        System.out.println(a==b);
//				String c = new String("abc");
//				System.out.println(a==c);
//				System.out.println(a==c.intern());
