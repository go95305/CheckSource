package com.ssafy.checksource.model.dto;

import com.ssafy.checksource.model.entity.Depart;
import com.ssafy.checksource.model.entity.Job;

import lombok.Data;

@Data
public class UserInfoDTO {

	private String gitlabId;
	private String name;
	private Long depart;
	private Long job;
}
