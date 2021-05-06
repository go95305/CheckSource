package com.ssafy.checksource.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.checksource.model.entity.LicenseToOpensource;


public interface LicenseToOpensourceRepository extends JpaRepository<LicenseToOpensource, Long>{

  
    public List<LicenseToOpensource> findByOpensource(long opensourceId);
    public List<LicenseToOpensource> findByLicense(long licenseId);
}
