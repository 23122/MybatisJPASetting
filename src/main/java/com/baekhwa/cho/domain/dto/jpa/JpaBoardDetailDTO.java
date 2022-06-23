package com.baekhwa.cho.domain.dto.jpa;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.baekhwa.cho.domain.entity.JpaBoardEntity;
import com.baekhwa.cho.domain.entity.ReplyEntity;

import lombok.Data;

@Data
public class JpaBoardDetailDTO {
	private long no;
	private String title;
	private String writer;
	private String content;
	private int readCount;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
//	private List<replyListDTO> replies;
	public JpaBoardDetailDTO(JpaBoardEntity e) {
		this.no = e.getNo();
		this.title = e.getTitle();
		this.writer = e.getWriter();
		this.content = e.getContent();
		this.readCount = e.getReadCount();
		this.createdDate = e.getCreatedDate();
		this.updatedDate = e.getUpdatedDate();
		//댓글처리
//		replies=e.getReplies().stream().map(replyListDTO::new).collect(Collectors.toList());
	}
	
	
}
