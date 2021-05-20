package com.ssafy.checksource.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class AnalyProjectListDTO {

	private Long projectId; //프로젝트 테이블 Id
	private String gitProjectId;
	private String projectName;
	private String branch;
	private String webUrl;
	private List<RepositoryTreeDTO> packageManageFileList;
	private List<GithubRepoPathDTO> githubpackageManageFileList;
}
