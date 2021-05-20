package com.ssafy.checksource.model.dto;

import lombok.Data;

@Data
public class GitHubConnectDTO {
    private Boolean flag;
    private Long githubId;
    private String userId;
    private String username;
}
