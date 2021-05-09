package com.ssafy.checksource.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "opensource_project")
@NoArgsConstructor
@Data
public class OpensourceProject {

	@Id
	@Column(name = "mapping_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long mappingId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "opensource_id")
	private Opensource opensource;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	private Project project;
	
}
