package com.ssafy.checksource.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.checksource.model.entity.LicenseOpensource;

public interface LicenseOpensourceRepository extends JpaRepository<LicenseOpensource, Long> {

	public List<LicenseOpensource> findByOpensource(long opensourceId);

	public List<LicenseOpensource> findByLicense(long licenseId);
	
	@Query(value = "select * from license_opensource where opensource_id " + 
			"in (select opensource_id from opensource_project where project_id = ?1)", nativeQuery = true)
	public List<LicenseOpensource> findAllByProjectId (String projectId);
}
