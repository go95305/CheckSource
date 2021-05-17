package com.ssafy.checksource.model.dto;

import lombok.Data;

@Data
public class GitHubProjectDTO {
    private String id;
    private String name;
    private String branch;
    private String html_url;
    private String sha;
    private boolean verified;
}
