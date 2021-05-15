package com.ssafy.checksource.model.entity;

import java.time.LocalDateTime;
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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Data
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "project_id")
	private Long projectId;
	
	@Column(name = "git_project_id")
	private String gitProjectId;
	
	private String name;
	
	@Column(columnDefinition = "bit default 0")
	private boolean status;
	
	@Column(name = "web_url")
	private String webUrl;
	
	@Column(name =  "git_type")
	private Long gitType;
	
	@CreationTimestamp
	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm", timezone="Asia/Seoul")
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private LocalDateTime date;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "depart_id")
	private Depart depart;
	
    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user; 
	
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<OpensourceProject> opensourceProject = new ArrayList<>();
	
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<UnmappedOpensource> unmappendOpensource = new ArrayList<>();
	
	private String branch;
}
