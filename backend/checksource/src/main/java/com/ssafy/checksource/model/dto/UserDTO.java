package com.ssafy.checksource.model.dto;

import java.util.List;

import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Git;

import lombok.Data;

@Data
public class UserDTO {
    
    private String token;
    private String name; 
    private boolean flag;
    private Integer userImg;
    private Long depart;
    private Long job;
    
    private List<GitLabDTO> gitlabList;
    private GitHubDTO gitHubDTO;
}

