package com.ssafy.checksource.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.checksource.model.entity.User;

public interface UserRepository extends JpaRepository<User, String>{

}
