package com.ssafy.checksource.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.ssafy.checksource.model.entity.LicenseOpensource;
import com.ssafy.checksource.model.entity.Opensource;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OpensourceDTO {
	
	private long opensourceId;
	private String name;
	private String url;
	private String copyright;
	private List<String> licenseNameList;

	public OpensourceDTO (LicenseOpensource licenseOpensource) {
		Opensource opensource = licenseOpensource.getOpensource();
		this.opensourceId = opensource.getOpensourceId();
		this.name = opensource.getName();
		this.url = opensource.getUrl();
		this.copyright = opensource.getCopyright();
		this.licenseNameList = new ArrayList<String>();
	}
}

