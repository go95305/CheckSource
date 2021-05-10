package com.ssafy.checksource.model.dto;

import com.ssafy.checksource.model.entity.LicenseOpensource;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectLiceseListDTO {

	private long licenseId;
	private String identifier;
	private String name;
	private String url;
	private String sourceopen;

	public ProjectLiceseListDTO (LicenseOpensource licenseOpensource) {
		this.licenseId = licenseOpensource.getLicense().getLicenseId();
		this.identifier = licenseOpensource.getLicense().getIdentifier();
		this.name = licenseOpensource.getLicense().getName();
		this.url = licenseOpensource.getLicense().getUrl();
		this.sourceopen = licenseOpensource.getLicense().getSourceopen();
	}

	
}
