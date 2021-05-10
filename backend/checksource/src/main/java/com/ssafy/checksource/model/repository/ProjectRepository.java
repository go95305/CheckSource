package com.ssafy.checksource.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.checksource.model.entity.Project;



public interface ProjectRepository extends JpaRepository<Project, String>{
	
	Project findByProjectId (String projectId);
	
	//Project findByDepartIDAnd
}
