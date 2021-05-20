package com.ssafy.checksource.model.repository;

import com.ssafy.checksource.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, String>{

    User findByUserId(String userId);

}
