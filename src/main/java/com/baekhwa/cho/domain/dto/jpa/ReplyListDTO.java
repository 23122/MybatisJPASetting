package com.baekhwa.cho.domain.dto.jpa;

import java.time.LocalDateTime;

import com.baekhwa.cho.domain.entity.ReplyEntity;

import lombok.Getter;

@Getter
public class ReplyListDTO {
	private long no;
	private String text;
	private String replier;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	
	public ReplyListDTO(ReplyEntity e) {
		this.no = e.getNo();
		this.text = e.getText();
		this.replier = e.getReplier();
		this.createdDate = e.getCreatedDate();
		this.updatedDate = e.getUpdatedDate();
		
	}
	
}
