package com.ssafy.checksource.model.key;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class GitLabUserKey implements Serializable{

	
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "gitlab_id")
	private Long gitlabId;
}
