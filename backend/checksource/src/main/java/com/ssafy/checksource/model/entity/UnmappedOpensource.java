package com.ssafy.checksource.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class UnmappedOpensource {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "unmappedopensource_id")
	private long unmappedopensourceId;
	
	@Column(name = "group_id")
	private String groupId;
	@Column(name = "artifact_id")
	private String artifactId;
	@Column(name = "version")
	private String version;
	private String path;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	private Project project;
}
