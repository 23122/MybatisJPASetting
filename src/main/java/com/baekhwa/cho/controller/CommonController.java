package com.baekhwa.cho.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {
	
	@GetMapping("/signup")
	public String singup() {
		return "view/common/signup";
	}
	
	@GetMapping("/signin")
	public String singin() {
		return "view/common/signin";
	}
}
