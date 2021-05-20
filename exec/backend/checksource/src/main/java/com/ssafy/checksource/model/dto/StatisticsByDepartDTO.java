package com.ssafy.checksource.model.dto;

import lombok.Data;

@Data
public class StatisticsByDepartDTO {

	private Long departId;
	private int projectCnt;
	private int opensourceCnt;
	private int licenseCnt;
	private int warningCnt;
}
