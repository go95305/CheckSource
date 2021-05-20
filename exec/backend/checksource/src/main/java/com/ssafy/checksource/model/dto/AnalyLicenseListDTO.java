package com.ssafy.checksource.model.dto;

import java.util.List;

import lombok.Data;

@Data 
public class AnalyLicenseListDTO {

	private int totalPages;
	private List<ProjectLiceseListDTO> licenseList;
	
}
