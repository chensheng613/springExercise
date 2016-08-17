package com.mypro.webservice;

import org.springframework.stereotype.Component;

@Component
public class HelloWorldService implements HelloInf{

	public String hello(){
		return "hello world";
	}

	public String sayToHello(String name) {
		return name;
	}
}
