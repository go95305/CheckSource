package com.ssafy.checksource.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class AnalyProjectListByDepartDTO {

	private int totalPages;
	private List<ProjectListByDepartDTO> projectList;
}
