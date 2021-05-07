package com.ssafy.checksource.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.checksource.model.entity.License;


public interface LicenseRepository extends JpaRepository<License, Long>{

    public List<License> findAll();
    public List<License> findByIdentifierLike(String identifier);
    public License findByLicenseId(long licenseId);
}
