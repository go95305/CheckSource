package com.ssafy.checksource.model.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.checksource.model.entity.Opensource;


public interface OpensourceRepository extends JpaRepository<Opensource, Long>{

    public Opensource findById(long id);
    public Opensource findByOpensourceId(long opensourceId);
    public Opensource findByGroupIdAndArtifactId(String groupId,String artifactId);
    public Opensource save(Opensource ops);
	public Page<Opensource> findByNameLike(String name, Pageable pageable);
	//public Page<Opensource> findByLicenseLike(String name, Pageable pageable);
}
