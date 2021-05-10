package com.ssafy.checksource.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "opensource")
@NoArgsConstructor
@Data
public class Opensource {
	@Id
	@Column(name = "opensource_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long opensourceId;
	
	private String name;
	private String url;
	private String copyright;
	private String version;
	private String packageType;
	@Column(name = "group_id")
	private String groupId;
	@Column(name = "artifact_id")
	private String artifactId;
	
	@OneToMany(mappedBy = "opensource", cascade = CascadeType.ALL)
    private List<LicenseOpensource> licenses = new ArrayList<>();
	
	
	@OneToMany(mappedBy = "opensource", cascade = CascadeType.ALL)
    private List<OpensourceProject> opensourceProject = new ArrayList<>();
}
//