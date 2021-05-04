package com.ssafy.checksource.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "gitlab")
public class GitLab {
	@Id
	@Column(name = "gitlab_id")
	private String gitlabId;
	private String name;
	private String username;
	private String state;
	@Column(name = "avatar_url")
	private String avatarUrl;
	@Column(name = "web_url")
	private String webUrl;
	
	private String accessToken;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
}
