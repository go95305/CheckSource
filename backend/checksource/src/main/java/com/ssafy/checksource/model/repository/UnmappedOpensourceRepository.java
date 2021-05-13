package com.ssafy.checksource.model.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.checksource.model.entity.UnmappedOpensource;


public interface UnmappedOpensourceRepository extends JpaRepository<UnmappedOpensource, Long>{
	
	@Query( value = "delete from unmapped_opensource where project_id = ?1",nativeQuery = true)
	void deleteAllByProjectId (String projectId);
	
}
