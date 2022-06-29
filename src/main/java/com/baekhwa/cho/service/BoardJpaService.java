package com.baekhwa.cho.service;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.baekhwa.cho.domain.dto.jpa.JpaBoardInsertDTO;
import com.baekhwa.cho.domain.dto.jpa.JpaBoardUpdateDTO;
import com.baekhwa.cho.domain.dto.jpa.ReplyInsertDTO;

public interface BoardJpaService {

	void list(int pageNo, Model model);

	String detail(long no, Model model);

	String update(JpaBoardUpdateDTO dto, long no);

	String delete(long no);

	boolean reply(ReplyInsertDTO dto);

	String replies(long bno, Model model);

	String save(JpaBoardInsertDTO dto, MultipartFile[] file);

	String fileUpload(MultipartFile file, String prevImgName);

}
