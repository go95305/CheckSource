package com.ssafy.checksource.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.checksource.model.entity.Depart;
import com.ssafy.checksource.model.entity.Project;



public interface ProjectRepository extends JpaRepository<Project, String>{
	
	Project findByProjectId (String projectId);
	
	List<Project> findByDepart (Depart depart);
}
