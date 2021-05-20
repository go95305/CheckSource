package com.ssafy.checksource.model.dto;

import lombok.Data;

@Data
public class PackageManageFileDTO {
	
	private String file_name;
	private String file_path;
	private String content;
	
}
