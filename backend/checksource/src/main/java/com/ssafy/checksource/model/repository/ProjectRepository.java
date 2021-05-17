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
	
	List<Project> findAllByDepart (Depart depart);
	
	@Query(value = "select * from project where depart_id = ?1 and date <= ?2 order by date desc", nativeQuery = true)
	Page<Project> findByDepart (Long departId, Pageable pageable, String time);
	
	@Query(value = "select * from project where depart_id = ?1 and date <= ?2 and name like ?3 order by date desc", nativeQuery = true)
	Page<Project> findByDepartAndKeyword (Long departId, Pageable pageable, String time, String name);
	
	Project findByGitProjectIdAndGitType (String gitProjectId, Long gitType);
	
	
	
	//전체 라이선스 warning
	@Query(value = "select q.depart_id, q.project_id, count(q.project_id) as count from license as c join " + 
			"(select p.project_id, l.opensource_id, l.license_id, p.date, p.depart_id from license_opensource as l " + 
			"join " + 
			"(select o.* , p.date, p.depart_id from opensource_project as o join " + 
			"project as p " + 
			"on o.project_id = p.project_id " + 
			"where p.date <= ?1 " + 
			") as p " + 
			"on l.opensource_id = p.opensource_id) as q " + 
			"on c.license_id = q.license_id " + 
			"where length(c.sourceopen) > 0 " + 
			"group by q.project_id " + 
			"order by q.date desc", nativeQuery = true)
	List<Object[]> findByLicenseWarningTotalProjects (String time, Pageable pageable);
	
	
	//부서별 라이선스 warning
	@Query(value = "select q.project_id, count(q.project_id) as count from license as c join " + 
			"(select p.project_id, l.opensource_id, l.license_id, p.date from license_opensource as l " + 
			"join " + 
			"(select o.*, p.date from opensource_project as o join " + 
			"project as p " + 
			"on o.project_id = p.project_id " + 
			"where p.depart_id = ?1 and p.date <= ?2" + 
			") as p " + 
			"on l.opensource_id = p.opensource_id) as q " + 
			"on c.license_id = q.license_id " + 
			"where length(c.sourceopen) > 0 " + 
			"group by q.project_id " + 
			"order by q.date desc", 
			nativeQuery = true)
	List<Object[]> findByLicenseWarningProjectByDepart (Long departId, String time, Pageable pageable);
}
