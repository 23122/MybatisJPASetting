package com.baekhwa.cho.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginDTO {
	
	private String email;
	private String name;
	
	public LoginDTO(MemberDTO dto){
		email=dto.getEmail();
		name=dto.getName();
	}
}
