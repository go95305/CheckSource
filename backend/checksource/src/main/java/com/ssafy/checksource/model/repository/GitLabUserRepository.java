package com.ssafy.checksource.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.checksource.model.entity.GitLabUser;
import com.ssafy.checksource.model.entity.LicenseOpensource;
import com.ssafy.checksource.model.entity.User;

public interface GitLabUserRepository extends JpaRepository<GitLabUser, Long>{
	
	
	List<GitLabUser> findByUser (User user);

}
