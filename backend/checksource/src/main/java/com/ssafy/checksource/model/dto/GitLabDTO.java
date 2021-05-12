package com.ssafy.checksource.model.dto;

import lombok.Data;

@Data
public class GitLabDTO {

	private Long gitlabId;
	private String baseUrl;
	private String userGitlabId;
	private String username;

}
