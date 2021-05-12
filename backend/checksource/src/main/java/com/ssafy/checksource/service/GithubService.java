package com.ssafy.checksource.service;

import com.ssafy.checksource.config.security.JwtTokenProvider;
import com.ssafy.checksource.model.dto.GitHubConnectDTO;
import com.ssafy.checksource.model.entity.GithubUser;
import com.ssafy.checksource.model.entity.User;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.*;
import com.ssafy.checksource.model.repository.GitHubRepository;
import com.ssafy.checksource.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.sound.midi.Soundbank;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional

public class GithubService {
    @Autowired
    RestTemplate restTemplate;
    private final JwtTokenProvider jwtTokenProvider;
    private final ModelMapper modelMapper = new ModelMapper();
    private final GitHubRepository gitHubRepository;
    private final UserRepository userRepository;
    private final String baseURL = "https://api.github.com/";

    //github 계정 연동 체크
    public GitHubConnectDTO githubConnect(String username, String token,String personalAccessToken) {
//        GithubUser githubUser = gitHubRepository.findById(githubId).orElseThrow(() -> new IllegalArgumentException("no github data"));
//        String accessToken = githubUser.getPersonalAccessToken();
        String userId = jwtTokenProvider.getUserId(token);
        User user = userRepository.findByUserId(userId);

        String url = baseURL + "users/";
        url += username;
        GitHubConnectDTO gitHubConnectDTO = new GitHubConnectDTO();
        gitHubConnectDTO.setFlag(false);
        //헤더에 토큰 담기
        String s = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", personalAccessToken);
        HttpEntity entity = new HttpEntity(headers);
        try {
            //api요청
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            GithubUser githubUser = new GithubUser();
            githubUser.setUser(user);
            githubUser.setUsername(username);
            GithubUser githubSaveUser = gitHubRepository.save(githubUser);

            gitHubConnectDTO.setFlag(true);
            gitHubConnectDTO.setUserId(githubSaveUser.getUser().getUserId());
            gitHubConnectDTO.setGithubId(githubSaveUser.getGithubId());
            gitHubConnectDTO.setUsername(username);
            return gitHubConnectDTO;
        } catch (HttpClientErrorException e) {
            return gitHubConnectDTO;
        }
    }

    //github 계정  연결 끊기
    public void deleteGitConnect(Long githubId, String token) {
        Optional<GithubUser> githubUser = Optional.ofNullable(gitHubRepository.findById(githubId).orElseThrow(() -> new IllegalArgumentException("no github data")));
        gitHubRepository.delete(githubUser.get());
    }
}