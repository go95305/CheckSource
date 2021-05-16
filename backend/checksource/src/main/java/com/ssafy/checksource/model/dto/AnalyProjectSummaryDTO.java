package com.ssafy.checksource.model.dto;

import lombok.Data;

@Data
public class AnalyProjectSummaryDTO {
	
	private int analyLicenseCnt;
	private int analyOpensourceCnt;
	private int unmappingOpensourceCnt;
	private int requireCheckingLicenseCnt;
}
