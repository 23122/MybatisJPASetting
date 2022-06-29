package com.baekhwa.cho.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.baekhwa.cho.domain.dto.JpaBoardListDTO;
import com.baekhwa.cho.domain.dto.jpa.JpaBoardDetailDTO;
import com.baekhwa.cho.domain.dto.jpa.JpaBoardInsertDTO;
import com.baekhwa.cho.domain.dto.jpa.JpaBoardUpdateDTO;
import com.baekhwa.cho.domain.dto.jpa.ReplyInsertDTO;
import com.baekhwa.cho.domain.dto.jpa.ReplyListDTO;
import com.baekhwa.cho.domain.entity.FileEntity;
import com.baekhwa.cho.domain.entity.FileRepository;
import com.baekhwa.cho.domain.entity.JpaBoardEntity;
import com.baekhwa.cho.domain.entity.JpaBoardEntityRepository;
import com.baekhwa.cho.domain.entity.ReplyEntityRepository;
import com.baekhwa.cho.service.BoardJpaService;
import com.baekhwa.cho.util.PageInfo;

@Service
public class BoardJpaSeriveProc implements BoardJpaService {

	@Autowired
	private JpaBoardEntityRepository repository;
	
	@Autowired
	private ReplyEntityRepository replyEntityRepository;
	
	@Transactional
	@Override
	public String save(JpaBoardInsertDTO dto, MultipartFile[] file) {
		System.out.println(">>>>>>>>>>>>>>>>" + file);
		JpaBoardEntity entity=dto.toEntity();
		for (MultipartFile f : file) {
			if (!f.isEmpty()) {
				//이름중복처리
//				String fileOriginalName = f.getOriginalFilename();				
//				String[] strs = fileOriginalName.split("[.]");
//				String extension="."+strs[strs.length-1];
//				String fileChangeName=System.nanoTime()+extension;
//				UUID uuid=UUID.randomUUID();
//				String fileChangeName = System.nanoTime() + "_" + fileOriginalName;
				
//				//src경로
//				String url2 = "E:/java/spring/baekhwa-project/src/main/resources/static/upload";
				
				//lunux 이용시
//				String linux = "/home/ec2-user/src/root";
				
				//bin경로
				String url = "/upload/";
				ClassPathResource cprTemp = new ClassPathResource("static" + url+"temp");

				try {
					File newFile=new File(cprTemp.getFile(), dto.getFileChangeName());
					ClassPathResource uploadDir = new ClassPathResource("static" + url);
					File dest=new File (uploadDir.getFile(),dto.getFileChangeName());
					newFile.renameTo(dest);
					entity.addFile(FileEntity.builder()
							.fileUrl(url)
							.fileSize(f.getSize())
							.fileOriginalName(f.getOriginalFilename())
							.fileChangeName(dto.getFileChangeName())
							.build());
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
//			System.out.println("파일존재유무 : "+f.isEmpty());
//			System.out.println("파라미터이름 : "+f.getName());
//			System.out.println("업로드파일이름 : "+f.getOriginalFilename());
//			System.out.println("파일크기(byte) : "+f.getSize());
//			System.out.println("파일타입 : "+f.getContentType());
//			System.out.println("-------------------------------");
		}
		repository.save(entity);
		return "redirect:/boardjpa";
	}

	@Override
	public void list(int pageNo, Model model) {// request scope 와 동일 : Model -> view까지 전달
//		List<JpaBoardListDTO> result=repository.findAll() /*List<JpaBoardEntity>*/
//												.stream() /*Stream<JpaBoardEntity>*/
//			    								.map(JpaBoardListDTO::new) /*.map(e-> JpaBoardListDTO(e))*/
//												.collect(Collectors.toList());
//		model.addAttribute("list", result);

		int page = pageNo - 1;
		int size = 3;
		Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.DESC, "no"));
		Page<JpaBoardEntity> pageObj = repository.findAll(pageable);
		int pageTotal = pageObj.getTotalPages();
		PageInfo pageInfo = PageInfo.getInstance(pageNo, pageTotal);
		model.addAttribute("pi", pageInfo);
		model.addAttribute("list", pageObj.getContent().stream().map(JpaBoardListDTO::new).collect(Collectors.toList()));

		/*
		 * java 8 version under List<JpaBoardEntity> r=repository.findAll();
		 * List<JpaBoardListDTO> list=new Vector<>(); for(JpaBoardEntity e: r) {
		 * JpaBoardListDTO dto=new JpaBoardListDTO(e); list.add(dto); }
		 */
	}
	
	@Transactional
	@Override
	public String detail(long no, Model model) {
		model.addAttribute("detail", repository.findById(no).map(JpaBoardDetailDTO::new).get());
		return "view/boardjpa/detail";
	}

	@Transactional
	@Override
	public String update(JpaBoardUpdateDTO dto, long no) {
		repository.findById(no).map(e -> e.update(dto));
		/*
		 * @Transactional 미사용시 //수정데이터 존재확인 JpaBoardEntity
		 * entity=repository.findById(no).orElseThrow(); //입력데이터 entity.update(dto);
		 * //업데이트 쿼리 repository.save(entity);
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

	// 지정된 게시글의 댓글가져오기
	@Override
	public String replies(long bno, Model model) {
		List<ReplyListDTO> result = replyEntityRepository.findAllByJpaBoardEntityNo(bno, Sort.by(Direction.DESC, "no"))
				.stream().map(ReplyListDTO::new).collect(Collectors.toList());
		model.addAttribute("list", result);
		return "view/boardjpa/reply/list";
	}

	@Override
	public String fileUpload(MultipartFile file,String prevImgName) {
		String fileOriginalName = file.getOriginalFilename();
		// 이름중복처리
		String fileChangeName = System.nanoTime() + "_" + fileOriginalName;
		// bin경로
		String url = "/upload/temp/";
		ClassPathResource cpr = new ClassPathResource("static" + url);
		try {
			File location=cpr.getFile();
			File prevImg = new File(location,prevImgName);
			if(prevImg.isFile())prevImg.delete();
			file.transferTo(new File(location, fileChangeName));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return fileChangeName;
	}

}
