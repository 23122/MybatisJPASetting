package com.baekhwa.cho.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.baekhwa.cho.domain.dto.JpaBoardListDTO;
import com.baekhwa.cho.domain.dto.jpa.JpaBoardDetailDTO;
import com.baekhwa.cho.domain.dto.jpa.JpaBoardInsertDTO;
import com.baekhwa.cho.domain.dto.jpa.JpaBoardUpdateDTO;
import com.baekhwa.cho.domain.dto.jpa.ReplyInsertDTO;
import com.baekhwa.cho.domain.dto.jpa.ReplyListDTO;
import com.baekhwa.cho.domain.entity.JpaBoardEntity;
import com.baekhwa.cho.domain.entity.JpaBoardEntityRepository;
import com.baekhwa.cho.domain.entity.ReplyEntityRepository;
import com.baekhwa.cho.service.BoardJpaService;
import com.baekhwa.cho.util.PageInfo;

@Service
public class BoardJpaSeriveProc implements BoardJpaService{
	
	@Autowired
	private JpaBoardEntityRepository repository;
	
	@Autowired
	private ReplyEntityRepository replyEntityRepository;
	
	@Override
	public String save(JpaBoardInsertDTO dto) {
		repository.save(dto.toEntity());
		return "redirect:/boardjpa";
	}

	@Override
	public void list(int pageNo,Model model) {// request scope 와 동일 : Model -> view까지 전달
//		List<JpaBoardListDTO> result=repository.findAll() /*List<JpaBoardEntity>*/
//												.stream() /*Stream<JpaBoardEntity>*/
//			    								.map(JpaBoardListDTO::new) /*.map(e-> JpaBoardListDTO(e))*/
//												.collect(Collectors.toList());
//		model.addAttribute("list", result);
		
		int page=pageNo-1;
		int size=3;
		Pageable pageable=PageRequest.of(page, size, Sort.by(Direction.DESC,"no"));
		Page<JpaBoardEntity> pageObj=repository.findAll(pageable);
		int pageTotal=pageObj.getTotalPages();
		PageInfo pageInfo=PageInfo.getInstance(pageNo, pageTotal);
		model.addAttribute("pi", pageInfo);
		model.addAttribute("list",pageObj.getContent().stream().map(JpaBoardListDTO::new).collect(Collectors.toList()));

		/*java 8 version under
		List<JpaBoardEntity> r=repository.findAll();
		List<JpaBoardListDTO> list=new Vector<>();
		for(JpaBoardEntity e: r) {
			JpaBoardListDTO dto=new JpaBoardListDTO(e);
			list.add(dto);
		}
		*/
	}

	@Override
	public String detail(long no, Model model) {								   
		model.addAttribute("detail",repository.findById(no).map(JpaBoardDetailDTO::new).get());
		return "view/boardjpa/detail";
	}
	
	@Transactional
	@Override
	public String update(JpaBoardUpdateDTO dto, long no) {
		repository.findById(no).map(e->e.update(dto));
		/*@Transactional 미사용시
		//수정데이터 존재확인
		JpaBoardEntity entity=repository.findById(no).orElseThrow();
		//입력데이터
		entity.update(dto);
		//업데이트 쿼리
		repository.save(entity);
		*/
		return "redirect:/boardjpa/{no}";
	}

	@Override
	public String delete(long no) {
		repository.deleteById(no);
		return "redirect:/boardjpa/list";
	}
	
	@Transactional
	@Override
	public boolean reply(ReplyInsertDTO dto) {
		replyEntityRepository.save(dto.toEntity());
		return true;
		
	}
	
	//지정된 게시글의 댓글가져오기
	@Override
	public String replies(long bno, Model model) {
		List<ReplyListDTO> result= replyEntityRepository.findAllByJpaBoardEntityNo(bno, Sort.by(Direction.DESC, "no"))
				.stream().map(ReplyListDTO::new).collect(Collectors.toList());
		model.addAttribute("list", result);
		return "view/boardjpa/reply/list";
	}

}
