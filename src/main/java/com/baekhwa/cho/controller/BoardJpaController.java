package com.baekhwa.cho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baekhwa.cho.domain.dto.jpa.JpaBoardInsertDTO;
import com.baekhwa.cho.domain.dto.jpa.JpaBoardUpdateDTO;
import com.baekhwa.cho.domain.dto.jpa.ReplyInsertDTO;
import com.baekhwa.cho.service.BoardJpaService;

@Controller
public class BoardJpaController {
	
	@Autowired
	private BoardJpaService service;
	
	@GetMapping("/boardjpa")
	public String list(@RequestParam(defaultValue = "1") int pageNo,Model model) {
		service.list(pageNo,model);
		return "view/boardjpa/list";
	}
	
	@GetMapping("/boardjpa/write")
	public String write() {
		return "view/boardjpa/write";
	}
	
	@PostMapping("/boardjpa/write")
	public String write(JpaBoardInsertDTO dto) {
		return service.save(dto);
	}
	
	@GetMapping("/boardjpa/{no}")
	public String detail(@PathVariable long no,Model model) {
		return service.detail(no,model);
	}
	
	@PutMapping("/boardjpa/{no}")
	public String update(@PathVariable long no,JpaBoardUpdateDTO dto) {
		return service.update(dto,no);
	}
	
	@ResponseBody
	@DeleteMapping("/boardjpa/{no}")
	public String delete(@PathVariable long no) {
		return service.delete(no);
	}
	
	@ResponseBody
	@PostMapping("boardjpa/{bno}/reply")
	public boolean reply(ReplyInsertDTO dto) {
		return service.reply(dto);
	}
	
	@GetMapping("boardjpa/{bno}/replies")
	public String replies(@PathVariable long bno, Model model) {
		return service.replies(bno,model);
	}
	/*
	 * @DeleteMapping("boardjpa/{bno}/reply") public String deleteRe(@PathVariable
	 * long bno) { return service.delete(bno); }
	 */
}
