package com.ssafy.checksource.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ssafy.checksource.model.dto.*;
import com.ssafy.checksource.model.entity.*;
import com.ssafy.checksource.model.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.checksource.config.security.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final SsoRepository ssoRepository;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final DepartRepository departRepository;
    private final GitLabRepository gitLabRepository;
    private final JobRepository jobRepository;
    private final GitLabUserRepository gitLabUserRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private final GitHubRepository gitHubRepository;

    //로그인 API요청
    public boolean loginApi(SsoDTO ssoDto) {
        String userId = ssoDto.getUserId();
        String password = ssoDto.getPassword();
        Login loginUser = ssoRepository.findByUserIdAndPassword(userId, password);
        if (loginUser != null)
            return true;
        return false;
    }

    //회원정보 입력여부 체크
    public UserDTO checkUser(String userId) {
        User user = userRepository.findByUserId(userId);
        UserDTO userDto = new UserDTO();


        if (user != null) {// 최초로그인 x
            if (user.getFlag()) {// 회원 정보 o -> 로그인 성공
                String token = jwtTokenProvider.generateToken(user.getUserId()); // 토큰 새로 발급
                user.setToken(token);
                userRepository.save(user);
                //userDto set
                userDto.setToken(user.getToken());
                userDto.setUserImg(user.getUserImg());
                userDto.setName(user.getName());
                userDto.setFlag(user.getFlag());
                userDto.setJob(user.getJob().getJobId());
                userDto.setDepart(user.getDepart().getDepartId());

                //깃랩 정보
                List<GitLabUser> gitlabList = gitLabUserRepository.findByUser(user);
                List<GitLabDTO> gitlabListDto = new ArrayList<GitLabDTO>();
                for (GitLabUser gitlabUser : gitlabList) {
                    GitLabDTO gitlabDto = new GitLabDTO();
                    gitlabDto.setGitlabId(gitlabUser.getGitlab().getGitlabId());
                    gitlabDto.setUserGitlabId(gitlabUser.getUserGitlabId());
                    gitlabDto.setUsername(gitlabUser.getUsername());
                    gitlabDto.setBaseUrl(gitlabUser.getGitlab().getBaseUrl());
                    gitlabListDto.add(gitlabDto);
                }
                userDto.setGitlabList(gitlabListDto);

                List<GithubUser> githubList = gitHubRepository.findByUser(user);
                List<GitHubDTO> githubListDto = new ArrayList<>();
                for(GithubUser gitHubUser : githubList ){
                    GitHubDTO gitHubDTO = new GitHubDTO();
                    gitHubDTO.setUsername(gitHubUser.getUsername());
                    gitHubDTO.setGithubId(gitHubUser.getGithubId());
                    githubListDto.add(gitHubDTO);
                }
                userDto.setGithubList(githubListDto);
                return userDto;
            }
            //최초로그인은 아니지만 회원정보 미입력시 flag = false
        } else {// 최초 로그인 o -> user테이블에 저장
            if (ssoRepository.findById(userId).isPresent()) {
                User newUser = new User();
                newUser.setUserId(userId);
                userRepository.save(newUser);
            }
        }

        return userDto;
    }


    //회원정보 최초 입력 -> 로그인 성공시 회원정보 반환
    public UserDTO saveUserForm(UserInputDTO userInputDto) {
        User user = userRepository.findById(userInputDto.getUserId()).orElseThrow(() -> new IllegalArgumentException("no user data"));
        UserDTO userDto = new UserDTO();

        String token = jwtTokenProvider.generateToken(user.getUserId()); //토큰생성
        user = modelMapper.map(userInputDto, User.class);
        user.setToken(token);
        Depart depart = departRepository.findById(userInputDto.getDepart()).orElseThrow(() -> new IllegalArgumentException("no depart data"));
        Job job = jobRepository.findById(userInputDto.getJob()).orElseThrow(() -> new IllegalArgumentException("no job data"));
        user.setDepart(depart);
        user.setJob(job);
        user.setUserImg(1); //기본 이미지 1로 설정
        user.setFlag(true); //회원정보 입력시 flag = true
        userRepository.save(user);
        userDto = modelMapper.map(user, UserDTO.class);

        return userDto;
    }

    //로그아웃
    public void logout(String token) {
        String userId = jwtTokenProvider.getUserId(token);
        User user = userRepository.findByUserId(userId);
        user.setToken(null);
        userRepository.save(user);
    }

    //회원정보 수정
    public UserDTO updateUserForm(String token, UserUpdateDTO userUpdateDto) {
        String userId = jwtTokenProvider.getUserId(token);
        User user = userRepository.findByUserId(userId);
        UserDTO userDto = new UserDTO();

        Depart depart = departRepository.findById(userUpdateDto.getDepart()).orElseThrow(() -> new IllegalArgumentException("no depart data"));
        Job job = jobRepository.findById(userUpdateDto.getJob()).orElseThrow(() -> new IllegalArgumentException("no job data"));
        user.setDepart(depart);
        user.setJob(job);
        user.setName(userUpdateDto.getName());
        user.setUserImg(userUpdateDto.getUserImg());
        userRepository.save(user);

        //userDto set
        userDto.setToken(user.getToken());
        userDto.setUserImg(user.getUserImg());
        userDto.setName(user.getName());
        userDto.setFlag(user.getFlag());
        userDto.setJob(user.getJob().getJobId());
        userDto.setDepart(user.getDepart().getDepartId());

        //깃랩 정보
        List<GitLabUser> gitlabList = gitLabUserRepository.findByUser(user);
        List<GitLabDTO> gitlabListDto = new ArrayList<GitLabDTO>();
        for (GitLabUser gitlabUser : gitlabList) {
            GitLabDTO gitlabDto = new GitLabDTO();
            gitlabDto.setGitlabId(gitlabUser.getGitlab().getGitlabId());
            gitlabDto.setUserGitlabId(gitlabUser.getUserGitlabId());
            gitlabDto.setUsername(gitlabUser.getUsername());
            gitlabDto.setBaseUrl(gitlabUser.getGitlab().getBaseUrl());
            gitlabListDto.add(gitlabDto);
        }

        userDto.setGitlabList(gitlabListDto);

        return userDto;
    }

}
