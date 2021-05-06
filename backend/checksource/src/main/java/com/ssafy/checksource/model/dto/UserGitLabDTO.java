package com.ssafy.checksource.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserGitLabDTO {
	
	private String id;
	private String name;
	private String username;
	private String state;
	private String avatar_url;
	private String web_url;

}
