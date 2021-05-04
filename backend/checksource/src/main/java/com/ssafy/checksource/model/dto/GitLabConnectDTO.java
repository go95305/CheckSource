package com.ssafy.checksource.model.dto;

import lombok.Data;

@Data
public class GitLabConnectDTO {
	
	private boolean checkId;
	private boolean checkAccessToken;
	private String gitId;
	private String username;
}
