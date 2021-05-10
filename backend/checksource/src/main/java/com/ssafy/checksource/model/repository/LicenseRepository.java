package com.ssafy.checksource.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.checksource.model.entity.License;


public interface LicenseRepository extends JpaRepository<License, Long>{
	
	public Page<License> findByNameLike(String name, Pageable pageable);
    public Page<License> findByIdentifierLike(String identifier, Pageable pageable);
    public License findByLicenseId(long licenseId);
    
    
}
