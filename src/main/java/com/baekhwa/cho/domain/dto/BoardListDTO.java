package com.baekhwa.cho.domain.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
@Setter
public class BoardListDTO {
	
	private long no;
	private String title;
	private String writer;
	private int readCount;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	
	public BoardListDTO(BoardDTO dto) {
		this.no = dto.getNo();
		this.title = dto.getTitle();
		this.writer = dto.getWriter();
		this.readCount = dto.getReadCount();
		this.createdDate = dto.getCreatedDate();
		this.updatedDate = dto.getUpdatedDate();
	}
	
	

}
