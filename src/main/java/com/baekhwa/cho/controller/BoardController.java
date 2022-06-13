package com.baekhwa.cho.controller;

import javax.servlet.http.HttpSession;

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

import com.baekhwa.cho.domain.dto.BoardInsertDTO;
import com.baekhwa.cho.domain.dto.BoardUpdateDTO;
import com.baekhwa.cho.domain.dto.LoginDTO;
import com.baekhwa.cho.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@GetMapping("/board")
	public String list(@RequestParam(defaultValue = "1") int pageNo,Model model) {
		//db에서 데이터 읽어올꼐요...
		//페이지에 전달
//		service.boardListAll(model);
		service.boardList(pageNo, model);
		return "view/board/list";
	}
	
	@GetMapping("/board/write")
	public String write(HttpSession session) {
		LoginDTO loginfo=(LoginDTO) session.getAttribute("loginfo");
		if(loginfo==null) {
			return "redirect:/signin";
		}
		return "view/board/write";
	}
	
	@PostMapping("/board/write")
	public String write(BoardInsertDTO dto) {
		log.debug(">>>>"+dto);
		service.insert(dto);
		return "redirect:/board";
	}
	
	@GetMapping("/board/{bno}")//"board/detail?no="
	public String detail(@PathVariable("bno") int vno,Model model) {
		System.out.println(vno);
		service.detail(vno,model);
		return "view/board/detail";
	}
	
	@PutMapping("/board/{no}")
	public String edit(/*@PathVariable int no,*/BoardUpdateDTO dto) {
		service.update(dto);
		return "redirect:/board/"+dto.getNo();
	}
	//application.properties
	//spring.mvc.hiddenmethod.filter.enabled=true
	//then use @DeleteMapping
	@ResponseBody
	@DeleteMapping("/board/{no}")
	public void delete(@PathVariable int no) {
		service.delete(no);
	};
		
}
