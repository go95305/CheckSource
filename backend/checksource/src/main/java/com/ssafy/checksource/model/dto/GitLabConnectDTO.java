package com.ssafy.checksource.model.dto;

import lombok.Data;

@Data
public class GitLabConnectDTO {
	
	private boolean flag;
	private Long gitlabId;
	private String userGitlabId;
	private String username;
}
