package com.ssafy.checksource.model.dto;

import lombok.Data;

@Data
public class GithubRepoPathDTO {
    private String sha;
    private String url;
    private String path;
}
