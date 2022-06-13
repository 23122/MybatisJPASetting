package com.baekhwa.cho.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardInsertDTO {
	private String title;
	private String writer;
	private String content;
}
