package com.baekhwa.cho.service.impl;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.baekhwa.cho.domain.dto.LoginDTO;
import com.baekhwa.cho.domain.dto.MemberDTO;
import com.baekhwa.cho.domain.dto.MemberInsertDTO;
import com.baekhwa.cho.domain.dto.SigninDTO;
import com.baekhwa.cho.mybatis.mapper.MemberMapper;
import com.baekhwa.cho.service.MemberService;
import com.baekhwa.cho.util.EncryptUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberServiceProc implements MemberService {

	@Autowired
	private MemberMapper mapper;
	
	@Override
	public void save(MemberInsertDTO dto) {
		// DB에 dto정보 저장
		//필요한게 무엇일까요?
		//DB접근(jpa,my) DAO(repository 객체, mapper객체)은 무엇으로 결정?
		String encryptedPass=EncryptUtils.encryptSHA256(dto.getPass());
		System.out.println(dto.getPass()+":"+encryptedPass);
		dto.setPass(encryptedPass);
		int result=mapper.save(dto);
		
		log.debug(result+"명의 회원가입 정상완료!");
	}

	@Override
	public String signin(SigninDTO dto, HttpSession session, Model model) {
		//로그인실패시, 성공시
		//db에 입력된 이메일이 존재하는지 먼저 확인
		Optional<MemberDTO> result= mapper.findByEmail(dto.getEmail());
		if(result.isPresent()) {
			//회원이 존재하면
			//비밀번호 확인할께요..
			MemberDTO member=result.get();
			if(member.getPass().equals( EncryptUtils.encryptSHA256(dto.getPass()) ) ) {
				//비밀번호 일치한 경우
				//session에 로그인정보 저장하면됩니다.
				//???? 이름, email
				LoginDTO loginfo=LoginDTO.builder()
						.name(member.getName())
						.email(member.getEmail())
						.build();
				session.setAttribute("loginfo", loginfo);
				return "redirect:/";
			}
		}
		
		//로그인실패시
		//회원이 존재하지 않아요..
		model.addAttribute("errMsg", "존재하지 않는 계정 또는 비밀번호가 상이 합니다.");
		return "view/common/signin";
	}

	@Override
	public String signin2(SigninDTO dto, HttpSession session, Model model) {
		//존재유무
		Optional<MemberDTO> result=mapper.findByEmail(dto.getEmail());
		if(result.isPresent()) {
			//회원이 존재합니다.
			//패스워드 비교
			if(result.get().getPass().equals(EncryptUtils.encryptSHA256(dto.getPass()))) {
				//비밀번호일치
				//MemberDTO -> LoginDTO
				//LoginDTO loginfo=result.map(LoginDTO::new).get();
				session.setAttribute("loginfo", result.map(LoginDTO::new).get());
				return "redirect:/";
			}
		}
		
		//로그인실패시
		//회원이 존재하지 않아요..
		model.addAttribute("errMsg", "존재하지 않는 계정 또는 비밀번호가 상이 합니다.");
		return "view/common/signin";
	}

}
