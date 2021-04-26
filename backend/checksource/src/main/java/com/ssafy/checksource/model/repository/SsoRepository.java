package com.ssafy.checksource.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.checksource.model.entity.Login;

public interface SsoRepository extends JpaRepository<Login, Long>{

	Login findByUserIdAndPassword (Long userId, String password);
	
}
