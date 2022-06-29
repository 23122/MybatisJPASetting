package com.baekhwa.cho.domain.dto;

import com.baekhwa.cho.domain.entity.FileEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FileDTO {
	private long fileNo;
	private String fileUrl;
	private String fileOriginalName;
	private String fileChangeName;
	private long fileSize;
	
	public FileDTO(FileEntity e) {
		this.fileNo = e.getFileNo();
		this.fileUrl = e.getFileUrl();
		this.fileOriginalName = e.getFileOriginalName();
		this.fileChangeName = e.getFileChangeName();
		this.fileSize = e.getFileSize();
	}
}
