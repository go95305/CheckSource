package com.ssafy.checksource.model.dto;

import lombok.Data;

@Data
public class PomXmlDepenDTO {
    
	private String name;
    private String groupId;
    private String artifactId;
    private String version;
    private String license;
    
}