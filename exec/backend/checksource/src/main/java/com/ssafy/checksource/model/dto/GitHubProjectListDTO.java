package com.ssafy.checksource.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class GitHubProjectListDTO {
    private boolean accessflag;
    private List<GitHubProjectDTO> projectList;
}
