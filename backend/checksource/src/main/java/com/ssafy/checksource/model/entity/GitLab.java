package com.ssafy.checksource.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gitlab_id")
	private Long gitlabId;
	
	private String baseUrl;
	
	@Column(name = "access_token")
	private String accessToken;

	@OneToMany(mappedBy = "gitlab", cascade = CascadeType.ALL)
    private List<GitLabUser> gitLabUser = new ArrayList<>();
}
