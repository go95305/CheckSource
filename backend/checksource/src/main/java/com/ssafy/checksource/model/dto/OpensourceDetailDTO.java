package com.ssafy.checksource.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class OpensourceDetailDTO {
	
	private long opensourceId;
	private String name;
	private String url;
	private String copyright;
	private String version;
	private String packageType;
	private String groupId;
	private String artifactId;
	private List<LicenseDTO> licenseList; 
}

