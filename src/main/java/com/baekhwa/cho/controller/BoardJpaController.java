package com.baekhwa.cho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baekhwa.cho.domain.dto.jpa.JpaBoardInsertDTO;
import com.baekhwa.cho.domain.dto.jpa.JpaBoardUpdateDTO;
import com.baekhwa.cho.domain.dto.jpa.ReplyInsertDTO;
import com.baekhwa.cho.domain.entity.FileEntity;
import com.baekhwa.cho.domain.entity.FileRepository;
import com.baekhwa.cho.service.BoardJpaService;

@Controller
public class BoardJpaController {
	
	@Autowired
	private BoardJpaService service;
	
	@GetMapping("/boardjpa")
	public String list(@RequestParam(defaultValue = "1") int pageNo,Model model) {
		service.list(pageNo,model);
		return "view/boardjpa/list";
	}
	
	@GetMapping("/boardjpa/write")
	public String write() {
		return "view/boardjpa/write";
	}
	
	@PostMapping("/boardjpa/write")
	public String write(JpaBoardInsertDTO dto,MultipartFile[] file) {
		return service.save(dto,file);
	}
	
	@GetMapping("/boardjpa/{no}")
	public String detail(@PathVariable long no,Model model) {
		return service.detail(no,model);
	}
	
	@PutMapping("/boardjpa/{no}")
	public String update(@PathVariable long no,JpaBoardUpdateDTO dto) {
		return service.update(dto,no);
	}
	
	@ResponseBody
	@DeleteMapping("/boardjpa/{no}")
	public String delete(@PathVariable long no) {
		return service.delete(no);
	}
	
	@ResponseBody
	@PostMapping("boardjpa/{bno}/reply")
	public boolean reply(ReplyInsertDTO dto) {
		return service.reply(dto);
	}
	
	@GetMapping("boardjpa/{bno}/replies")
	public String replies(@PathVariable long bno, Model model) {
		return service.replies(bno,model);
	}
	
	@ResponseBody//성공시 문자열 리턴-> ajax success
	@PostMapping("/boardjpa/fileupload")
	public String fileUpload(MultipartFile file,String prevImgName) {
		return service.fileUpload(file,prevImgName);
	}
	
	@Autowired
	FileRepository fileRepository;
	
	@Autowired
	ResourceLoader resourceLoader;
	
	@ResponseBody
	@GetMapping(value =  "/boardjpa/files/{fileNo}",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Resource> downloadFile(@PathVariable(value = "fileNo") long fileNo,@RequestHeader("User-Agent") String userAgent)throws Exception {
		if(userAgent.contains("Edg")) {
			System.out.println("엣지브라우저");
		}else {
			System.out.println("크롬브라우저");
		}
		FileEntity entity =fileRepository.findById(fileNo).orElseThrow();
		
		Resource resource=resourceLoader.getResource("classpath:static/upload/"+entity.getFileChangeName());
		String fileName=new String(entity.getFileOriginalName().getBytes("UTF-8"),"ISO-8859-1");
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+fileName)
				.header(HttpHeaders.CONTENT_LENGTH,entity.getFileSize()+"")
				.body(resource);
	}
	/*
	 * @DeleteMapping("boardjpa/{bno}/reply") public String deleteRe(@PathVariable
	 * long bno) { return service.delete(bno); }
	 */
}
