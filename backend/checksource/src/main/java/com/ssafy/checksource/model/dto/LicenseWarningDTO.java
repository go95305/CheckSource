package com.ssafy.checksource.model.dto;

import lombok.Data;

@Data
public class LicenseWarningDTO {

	private Long projectId;
	private String name;
	private Long gitType;
	private int cnt;
	
}

