package com.ssafy.checksource.model.dto;

import lombok.Data;

@Data
public class GitLabConnectDTO {
	
	private boolean flag;
	private boolean accessflag;
	private String gitlabId;
	private String username;
}
