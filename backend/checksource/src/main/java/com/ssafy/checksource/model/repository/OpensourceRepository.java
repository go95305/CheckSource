package com.ssafy.checksource.model.repository;


import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.checksource.model.entity.Opensource;
import com.ssafy.checksource.model.entity.OpensourceProject;


public interface OpensourceRepository extends JpaRepository<Opensource, Long>{

    public Opensource findById(long id);
    public Opensource findByOpensourceId(long opensourceId);
    public Opensource findByGroupIdAndArtifactId(String groupId,String artifactId);
    public Opensource save(Opensource ops);
//    @Query(
//    		value = "select o.*, l.* "
//    				+ "from opensource o "
//    				+ "left outer join license_opensource lo on o.opensource_id = lo.opensource_id "
//    				+ "left outer join license l on lo.license_id = l.license_id "
//    				+ "where o.name like ?1",
//    		countQuery = "SELECT COUNT(*) FROM opensource",
//    		nativeQuery = true
//    		)
	public Page<Opensource> findByNameLike(String name, Pageable pageable);
	
	@Query(
			value = "select * from opensource where opensource_id in "
					+ "(select distinct opensource_id from license_opensource where license_id in "
					+ "(select license_id from license where name like ?1)) "
					+ "order by name",
					countQuery = "SELECT COUNT(*) FROM opensource",
					nativeQuery = true
			)
	public Page<Opensource> findByLicenseLike(String name, Pageable pageable);
	
	
	//부서별 오픈소스 종류 수
	@Query(value = "select * from opensource where opensource_id in " + 
			"(select distinct opensource_id from opensource_project where project_id in " + 
			"(select project_id from project where depart_id = ?1))", nativeQuery = true)
	List<Opensource> findByDepart(Long departId);
	
	//부서별 top5
	@Query(value = "select opensource_id, count(opensource_id) as count from opensource_project where project_id in " + 
			"(select project_id from project where depart_id = ?1) " + 
			"group by opensource_id " + 
			"order by count desc", nativeQuery = true)
	List<Object> findByTop5 (Long departId);
}
