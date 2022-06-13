package com.baekhwa.cho.service;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.baekhwa.cho.domain.dto.MemberInsertDTO;
import com.baekhwa.cho.domain.dto.SigninDTO;

public interface MemberService {

	void save(MemberInsertDTO dto);

	String signin(SigninDTO dto, HttpSession session, Model model);

	String signin2(SigninDTO dto, HttpSession session, Model model);

}
