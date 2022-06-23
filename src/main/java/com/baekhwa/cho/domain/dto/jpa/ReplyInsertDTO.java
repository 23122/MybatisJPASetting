package com.baekhwa.cho.domain.dto.jpa;

import com.baekhwa.cho.domain.entity.JpaBoardEntity;
import com.baekhwa.cho.domain.entity.ReplyEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyInsertDTO {
	private long bno;
	private String text;
	private String replier;
	public ReplyEntity toEntity() {
		return ReplyEntity.builder()
						  .replier(replier)
						  .text(text)
						  .jpaBoardEntity(JpaBoardEntity.builder()
								  						.no(bno)
								  						.build())
						  .build();
	}
}
