package com.mypro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/entry")
public class HomeController {

	@RequestMapping("/homePage")
	public String Entry() {
		return "homePage";
	}
}
