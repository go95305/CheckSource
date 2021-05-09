package com.ssafy.checksource.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.checksource.model.entity.LicenseOpensource;

public interface LicenseOpensourceRepository extends JpaRepository<LicenseOpensource, Long> {

	public List<LicenseOpensource> findByOpensource(long opensourceId);

	public List<LicenseOpensource> findByLicense(long licenseId);
}
