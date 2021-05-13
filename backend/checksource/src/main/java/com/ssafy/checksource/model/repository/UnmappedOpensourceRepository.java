package com.ssafy.checksource.model.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.checksource.model.entity.Project;
import com.ssafy.checksource.model.entity.UnmappedOpensource;


public interface UnmappedOpensourceRepository extends JpaRepository<UnmappedOpensource, Long>{

	List<UnmappedOpensource> findByProject (Project project);
}
