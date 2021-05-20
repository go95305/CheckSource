package com.ssafy.checksource.model.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ssafy.checksource.model.key.GitLabUserKey;

import lombok.Data;

@Entity
@Data
@Table(name = "GITLAB_USER")
public class GitLabUser {

	@EmbeddedId
	@Column(name="gitlab_user_key", unique = true)
	private GitLabUserKey gitlabUserKey = new GitLabUserKey();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gitlab_id", insertable=false, updatable=false)
	private GitLab gitlab;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", insertable=false, updatable=false)
	private User user;
	
	@Column(name = "user_gitlab_id")
	private String userGitlabId;
	private String username;
	@Column(name = "user_access_token")
	private String userAccessToken;
	

}
