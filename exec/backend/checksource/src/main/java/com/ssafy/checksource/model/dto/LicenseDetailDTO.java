package com.ssafy.checksource.model.dto;

import lombok.Data;

@Data
public class LicenseDetailDTO {

	private long licenseId;
	private String name;
	private String identifier;
	private String contents;
	private String url;
	private String userName;
	private Boolean is_osi;
	private Boolean is_modify;
	private Boolean is_license;
	private Boolean is_copyright;
	private String sourceopen;
	private Boolean is_anotherlicense;
	private Boolean is_modify_notice;
	private Boolean is_warranty;
	private Boolean is_respon;
	
}

