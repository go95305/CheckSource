package com.ssafy.checksource.model.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.checksource.model.entity.Depart;
import com.ssafy.checksource.model.entity.Project;



public interface ProjectRepository extends JpaRepository<Project, Long>{
	
	Project findByProjectId (Long projectId);
	
	@Query(value = "select * from project where depart_id = ?1 and date <= ?2 order by date desc", nativeQuery = true)
	Page<Project> findByDepart (Long departId, Pageable pageable, String time);
	
	Project findByGitProjectIdAndGitType (String gitProjectId, Long gitType);
}
