package com.ssafy.checksource.model.dto;

import lombok.Data;

@Data
public class LicenseDetailDTO {

	private long licenseId;
	private String name;
	private String identifier;
	private String sourceopen;
	private String contents;
	private String url;
	private boolean is_osi;
	private boolean is_copyright;
	private boolean is_license;
	private boolean is_commercial;
	private boolean is_distribute;
	private boolean is_modify;
	private boolean is_private;
	private boolean is_patent;
	private boolean is_respon;
	private boolean is_warranty;
	private boolean is_continue;
	private boolean is_status;
}

