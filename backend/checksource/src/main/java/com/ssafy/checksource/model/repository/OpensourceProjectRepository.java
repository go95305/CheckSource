package com.ssafy.checksource.model.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.checksource.model.entity.Opensource;
import com.ssafy.checksource.model.entity.OpensourceProject;
import com.ssafy.checksource.model.entity.Project;

public interface OpensourceProjectRepository extends JpaRepository<OpensourceProject, Long>{

//	@Query( value = "delete from opensource_project where opensource_id = ?1 and project_id = ?2",nativeQuery = true)
//	void deleteByOpensourceIdAndProjectId (Long opensourceId, String projectId);
	
	
	Page<OpensourceProject> findByProject (Project project, Pageable pageable);
	List<OpensourceProject> findAllByProject (Project project);
	
	@Query( value = "delete from opensource_project where git_project_id = ?1 and git_type = ?2",nativeQuery = true)
	void deleteAllByProjectId (String gitProjectId, Long gitType);

}
