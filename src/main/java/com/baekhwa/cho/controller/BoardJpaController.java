package com.baekhwa.cho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.baekhwa.cho.service.BoardJpaService;

@Controller
public class BoardJpaController {
	
	@Autowired
	private BoardJpaService service;
	
	@GetMapping("/boardjpa")
	public String list(Model model) {
//		service.findAll(model);
		return "view/boardjpa/list";
	}
}
