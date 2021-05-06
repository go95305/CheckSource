package com.ssafy.checksource.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class AnalyProjectListDTO {

	private String projectId;
	private String projectName;
	private List<RepositoryTreeDTO> packageManageFileList;
}
