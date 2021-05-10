package com.ssafy.checksource.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.checksource.model.entity.Opensource;
import com.ssafy.checksource.model.entity.OpensourceProject;

public interface OpensourceProjectRepository extends JpaRepository<OpensourceProject, Long>{

	@Query( value = "delete from opensource_project where opensource_id = ?1 and project_id = ?2",nativeQuery = true)
	void deleteByOpensourceIdAndProjectId (Long opensourceId, String projectId);
}
