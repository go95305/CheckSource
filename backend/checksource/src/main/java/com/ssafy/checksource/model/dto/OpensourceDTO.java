package com.ssafy.checksource.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class OpensourceDTO {
	
	private long opensourceId;
	private String name;
	private String url;
	private String copyright;
	private List<String> licenseNameList;
}

