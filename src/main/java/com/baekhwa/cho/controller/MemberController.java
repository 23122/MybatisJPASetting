package com.baekhwa.cho.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baekhwa.cho.domain.dto.LoginDTO;
import com.baekhwa.cho.domain.dto.MemberInsertDTO;
import com.baekhwa.cho.domain.dto.SigninDTO;
import com.baekhwa.cho.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/member")
@Controller
public class MemberController {
	
	//로직처리하기 위한 서비스
	@Autowired//
	private  MemberService service;

	//회원가입처리
	@PostMapping("signup")
	public String signup(MemberInsertDTO dto) {
		log.debug(">>> 회원가입처리 : "+ dto);
		//: 필요한게 무엇?
		//웹(View)-> 서버(Controller) 회원가입을 위한 데이터 전송
		//매핑메서드의 파라미터에 데이터 타입정의하면 되요 현재 3개의 데이터:MemberInsertDTO로 매핑
		//DB에 저장하도록 service에 지시
		service.save(dto);
		
		return "redirect:/signin";
	}
	
	//로그인처리
	@PostMapping("signin") //파라미터 매핑을 위해 DTO객체에 setter 생성
	public String signin(SigninDTO dto,HttpSession session, Model model) {
		log.debug(">>> 로그인처리 : "+ dto);
		return  service.signin2(dto, session, model);//성공시(인덱스), 실패시(로그인)
		//머가필요해요?
	}
	@GetMapping("signout") //파라미터 매핑을 위해 DTO객체에 setter 생성
	public String signout(HttpSession session) {
		log.debug(">>> 로그아웃 처리");
		session.removeAttribute("loginfo");
		//session.invalidate();//모든세션 삭제
		return  "redirect:/";
	}
	
	@ResponseBody// boolean 결과 리턴 ajax -> success의 함수의 result로
	@GetMapping("signin-check")
	public boolean signinCheck(HttpSession session) {
		log.debug(">>> 로그인체크");
		LoginDTO loginfo=(LoginDTO) session.getAttribute("loginfo");
		log.debug(">>>loginfo : "+loginfo);
		if(loginfo==null)return false;
		return true;
	}
}
