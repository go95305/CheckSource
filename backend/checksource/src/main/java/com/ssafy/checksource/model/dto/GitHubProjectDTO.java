package com.ssafy.checksource.model.dto;

import lombok.Data;

@Data
public class GitHubProjectDTO {
    private String id;
    private String name;
    private String branch;
    private boolean verified;
}
