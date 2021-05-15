package com.ssafy.checksource.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.checksource.model.entity.Depart;
import com.ssafy.checksource.model.entity.Project;



public interface ProjectRepository extends JpaRepository<Project, Long>{
	
	Project findByProjectId (Long projectId);
	
	List<Project> findByDepart (Depart depart);
	
	Project findByGitProjectIdAndGitType (String gitProjectId, Long gitType);
}
