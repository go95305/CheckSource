package com.ssafy.checksource.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class StatisticsTotalDTO {

	private int totalProjectCnt;
	private int totalOpensourceCnt;
	private int totalLicenseCnt;
	private int totalWarningCnt;
	private List<StatisticsByDepartDTO> statisticsList;
}
