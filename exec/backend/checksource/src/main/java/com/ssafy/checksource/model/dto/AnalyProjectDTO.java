package com.ssafy.checksource.model.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AnalyProjectDTO {

	private int opensourceCnt;
	private int licenseCnt;
	private int projectName;
	private int projectId;
	private int status;
	private LocalDateTime date;
	private String name; //검증 누른사람 이름
}
