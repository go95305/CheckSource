package com.ssafy.checksource.model.dto;

import lombok.Data;

@Data
public class ProjectInfoDTO {
	private String name;
	private String webUrl;
	private String branch;
	private boolean status;
}
