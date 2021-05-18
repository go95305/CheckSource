package com.ssafy.checksource.model.repository;

import com.ssafy.checksource.model.entity.GithubUser;
import com.ssafy.checksource.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GitHubRepository extends JpaRepository<GithubUser,Long> {


    GithubUser findByUserNameAndUser(String username, User user);

    List<GithubUser> findByUser(User user);
}
