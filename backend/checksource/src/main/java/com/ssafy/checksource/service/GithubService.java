package com.ssafy.checksource.service;

import com.ssafy.checksource.config.security.JwtTokenProvider;
import com.ssafy.checksource.model.dto.GitHubConnectDTO;
import com.ssafy.checksource.model.entity.GithubUser;
import com.ssafy.checksource.model.repository.GitHubRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Transactional

public class GithubService {
    @Autowired
    RestTemplate restTemplate;
    private final JwtTokenProvider jwtTokenProvider;
    private final ModelMapper modelMapper = new ModelMapper();
    private final GitHubRepository gitHubRepository;
    private final String baseURL = "https://api.github.com";
    //github 계정 연동 체크
    public GitHubConnectDTO githubConnect(String username, String token, Long githubId) {
        GithubUser githubUser = gitHubRepository.findById(githubId).orElseThrow(() -> new IllegalArgumentException("no github data"));

    }
}
