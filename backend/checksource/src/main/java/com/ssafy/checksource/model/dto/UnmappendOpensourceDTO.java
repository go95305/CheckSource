package com.ssafy.checksource.model.dto;

import com.ssafy.checksource.model.entity.UnmappedOpensource;

import lombok.Data;

@Data
public class UnmappendOpensourceDTO {

	private long unmappedopensourceId;
	private String groupId;
	private String artifactId;
	private String version;
	
	public UnmappendOpensourceDTO (UnmappedOpensource unmappedOpensource) {
		this.unmappedopensourceId = unmappedOpensource.getUnmappedopensourceId();
		this.groupId = unmappedOpensource.getGroupId();
		this.artifactId = unmappedOpensource.getArtifactId();
		this.version = unmappedOpensource.getVersion();
	}
}
