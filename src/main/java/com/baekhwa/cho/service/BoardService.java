package com.baekhwa.cho.service;

import org.springframework.ui.Model;

import com.baekhwa.cho.domain.dto.BoardInsertDTO;
import com.baekhwa.cho.domain.dto.BoardUpdateDTO;

public interface BoardService {

	void boardListAll(Model model);

	void insert(BoardInsertDTO dto);

	void detail(int vno, Model model);

	void update(BoardUpdateDTO dto);

	void delete(int no);

	void boardList(int pageNo, Model model);

}
