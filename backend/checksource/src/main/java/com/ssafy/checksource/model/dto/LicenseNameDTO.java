package com.ssafy.checksource.model.dto;

import com.ssafy.checksource.model.entity.License;

import lombok.Data;

@Data
public class LicenseNameDTO {
	private long licenseId;
	private String name;
	private String identifier;
	
	
	public LicenseNameDTO(License license) {
		this.licenseId = license.getLicenseId();
		this.name = license.getName();
		this.identifier = license.getIdentifier();
	}
}
