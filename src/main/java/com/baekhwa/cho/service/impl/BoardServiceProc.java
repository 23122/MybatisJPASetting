package com.baekhwa.cho.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.baekhwa.cho.domain.dto.BoardDTO;
import com.baekhwa.cho.domain.dto.BoardInsertDTO;
import com.baekhwa.cho.domain.dto.BoardListDTO;
import com.baekhwa.cho.domain.dto.BoardUpdateDTO;
import com.baekhwa.cho.mybatis.mapper.BoardMapper;
import com.baekhwa.cho.service.BoardService;
import com.baekhwa.cho.util.PageInfo;

@Service
public class BoardServiceProc implements BoardService {

	// dao ???
	@Autowired
	private BoardMapper mapper;

	@Override
	public void boardListAll(Model model) {
		// List<BoardDTO> to List<BoardListDTO>
		List<BoardListDTO> result = mapper.findAll().stream().map(BoardListDTO::new)// BoardDTO-->BoardListDTO :
																					// BoardListDTO(BoardDTO)
				.collect(Collectors.toList());
		model.addAttribute("list", result);

	}

	@Override
	public void boardList(int pageNo, Model model) {
		// 페이지 출력 게시물수
		int limit = 15;
		int offset = limit * (pageNo - 1);
		List<BoardListDTO> result = mapper.findFive(offset, limit);
		model.addAttribute("list", result);
		// 하단 페이지 이동처리
		int rowTotal = mapper.selectCount();// 총데이터 갯수
		model.addAttribute("pi",PageInfo.getInstance(pageNo, rowTotal, limit));
	}

	@Override
	public void insert(BoardInsertDTO dto) {
		mapper.insert(dto);
	}

	@Override
	public void detail(int vno, Model model) {
		BoardDTO result = mapper.detailBoard(vno).orElseThrow();
		mapper.updateReadCount(vno);
		model.addAttribute("detail", result);
	}

	@Override
	public void update(BoardUpdateDTO dto) {
		mapper.update(dto);
	}

	@Override
	public void delete(int no) {
		mapper.delete(no);
	}

}
