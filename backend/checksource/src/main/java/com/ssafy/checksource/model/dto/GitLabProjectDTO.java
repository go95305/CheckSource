package com.ssafy.checksource.model.dto;

import lombok.Data;

@Data
public class GitLabProjectDTO {

	private String id;
	private String name;
	private String branch;
	private boolean verified;
}
