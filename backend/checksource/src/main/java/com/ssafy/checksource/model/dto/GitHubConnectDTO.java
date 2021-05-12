package com.ssafy.checksource.model.dto;

import lombok.Data;

@Data
public class GitHubConnectDTO {
    private boolean flag;
    private Long githubId;
    private String userGithubId;
    private String username;
}
