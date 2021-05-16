package com.ssafy.checksource.model.dto;

import lombok.Data;

@Data
public class GithubPackageManageFileDTO {
    private String name;
    private String path;
    private String content;
}
