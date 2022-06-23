package com.baekhwa.cho.service;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.baekhwa.cho.domain.dto.jpa.JpaBoardInsertDTO;
import com.baekhwa.cho.domain.dto.jpa.JpaBoardUpdateDTO;
import com.baekhwa.cho.domain.dto.jpa.ReplyInsertDTO;

public interface BoardJpaService {

	String save(JpaBoardInsertDTO dto);

	void list(int pageNo, Model model);

	String detail(long no, Model model);

	String update(JpaBoardUpdateDTO dto, long no);

	String delete(long no);

	boolean reply(ReplyInsertDTO dto);

	String replies(long bno, Model model);

}
