package com.ssafy.checksource.model.dto;

import lombok.Data;

@Data
public class DependencyDTO {
    
    private String groupId;
    private String artifactId;
    private String version;
    
}

