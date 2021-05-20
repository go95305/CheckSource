package com.ssafy.checksource.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Data
@ToString
@Getter
public class GithubRepoTreeDTO {
    private String sha;
    private String url;
    private Tree tree[];
}
