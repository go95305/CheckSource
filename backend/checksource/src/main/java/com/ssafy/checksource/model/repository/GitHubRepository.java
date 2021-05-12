package com.ssafy.checksource.model.repository;

import com.ssafy.checksource.model.entity.GithubUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GitHubRepository extends JpaRepository<GithubUser,Long> {
}
