package com.baekhwa.cho.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "file")
public class FileEntity {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long fileNo;
	
	@Column(nullable = false)
	private String fileUrl;
	@Column(nullable = false)
	private String fileOriginalName;
	@Column(nullable = false)
	private String fileChangeName;
	@Column(nullable = false)
	private long fileSize;
	
}
