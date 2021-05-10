package com.ssafy.checksource.model.dto;

import lombok.Data;

@Data
public class ProjectLiceseListDTO {

	private long licenseId;
	private String identifier;
	private String contents;
	private String url;
	private boolean is_osi;
	private boolean is_copyright; //저작권 고지여부
	private boolean is_license;
	private boolean is_commercial; //상업적
	private boolean is_distribute;
	private boolean is_modify;
	private boolean is_private;
	private boolean is_patent;
	private boolean is_respon;
	private boolean is_warranty;
	private boolean is_sourceopen;
	private boolean is_continue;
	private boolean is_status;
}
