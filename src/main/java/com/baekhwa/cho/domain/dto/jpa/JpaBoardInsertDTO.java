package com.baekhwa.cho.domain.dto.jpa;

import com.baekhwa.cho.domain.entity.JpaBoardEntity;

import lombok.Data;

@Data
public class JpaBoardInsertDTO {
	private String title;
	private String writer;
	private String content;
	
	public JpaBoardEntity toEntity() {
		return JpaBoardEntity.builder()
							 .title(title)
							 .writer(writer)
							 .content(content)
							 .build();
	}
}
