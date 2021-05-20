package com.ssafy.checksource.model.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.checksource.model.entity.Project;
import com.ssafy.checksource.model.entity.UnmappedOpensource;


public interface UnmappedOpensourceRepository extends JpaRepository<UnmappedOpensource, Long>{

	Page<UnmappedOpensource> findByProject (Project project, Pageable pageable);
	
	List<UnmappedOpensource> findAllByProject (Project project);
	
	@Query( value = "delete from unmapped_opensource where git_project_id = ?1 and git_type = ?2",nativeQuery = true)
	void deleteAllByProjectId (String gitProjectId, Long gitType);
	
}
