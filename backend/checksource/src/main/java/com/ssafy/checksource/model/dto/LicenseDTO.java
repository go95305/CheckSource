package com.ssafy.checksource.model.dto;

import lombok.Data;

@Data
public class LicenseDTO {

	private long licenseId;
	private String name;
	private String identifier;
	private String sourceopen;
	private String url;
	private String userId;
}

