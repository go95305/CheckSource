package com.ssafy.checksource.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.checksource.model.entity.GitLab;
import com.ssafy.checksource.model.entity.User;


public interface GitLabRepository extends JpaRepository<GitLab, Long>{

	GitLab findByGitlabId (Long gitlabId);

}
