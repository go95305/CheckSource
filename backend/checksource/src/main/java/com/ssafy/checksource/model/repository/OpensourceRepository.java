package com.ssafy.checksource.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.checksource.model.entity.Opensource;


public interface OpensourceRepository extends JpaRepository<Opensource, Long>{

    public List<Opensource> findAll();
    public List<Opensource> findByNameLike(String name);
    public Opensource findByOpensourceId(long opensourceId);
    public Opensource findByGroupIdAndArtifactId(String groupId,String artifactId);

}
