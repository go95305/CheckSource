package com.ssafy.checksource.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class GitLabProjectListDTO {

	private List<GitLabProjectDTO> verifiedList;
	private List<GitLabProjectDTO> unverifiedList;
}
