package com.baekhwa.cho.domain.dto;

import java.time.LocalDateTime;

import com.baekhwa.cho.domain.entity.JpaBoardEntity;

import lombok.Getter;

@Getter
public class JpaBoardListDTO {
	private long no;
	private String title;
	private String writer;
	private int readCount;
	private LocalDateTime updatedDate;
	
	public JpaBoardListDTO(JpaBoardEntity e) {
		no=e.getNo();
		title=e.getTitle();
		writer=e.getWriter();
		readCount=e.getReadCount();
		updatedDate=e.getUpdatedDate();
	}
}
