package com.baekhwa.cho.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
public class SigninDTO {
	private String email;
	private String pass;
}
