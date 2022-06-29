package com.baekhwa.cho.domain.dto.jpa;

import com.baekhwa.cho.domain.entity.JpaBoardEntity;
import com.baekhwa.cho.domain.entity.MemberEntity;

import lombok.Data;

@Data
public class JpaBoardInsertDTO {
	private String title;
	private String content;
	private long memberNo;
	private String fileChangeName;
	
	public JpaBoardEntity toEntity() {
		return JpaBoardEntity.builder()
							 .title(title)
							 .content(content)
							 .memberEntity(MemberEntity.builder().memberNo(memberNo).build())
							 .build();
	}
}
