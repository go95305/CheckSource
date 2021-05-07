package com.ssafy.checksource.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ssafy.checksource.config.security.JwtTokenProvider;
import com.ssafy.checksource.model.dto.AnalyProjectListDTO;
import com.ssafy.checksource.model.dto.GitLabConnectDTO;
import com.ssafy.checksource.model.dto.GitLabProjectDTO;
import com.ssafy.checksource.model.dto.GitLabProjectListDTO;
import com.ssafy.checksource.model.dto.RepositoryTreeDTO;
import com.ssafy.checksource.model.dto.UserGitLabDTO;
import com.ssafy.checksource.model.entity.Depart;
import com.ssafy.checksource.model.entity.GitLab;
import com.ssafy.checksource.model.entity.Project;
import com.ssafy.checksource.model.entity.User;
import com.ssafy.checksource.model.repository.DepartRepository;
import com.ssafy.checksource.model.repository.GitLabRepository;
import com.ssafy.checksource.model.repository.ProjectRepository;
import com.ssafy.checksource.model.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class GitService {

	@Autowired
	RestTemplate restTemplate;
	private final JwtTokenProvider jwtTokenProvider;
	private final ModelMapper modelMapper = new ModelMapper();
	private final GitLabRepository gitLabRepository;
	private final UserRepository userRepository;
	private final ProjectRepository projectRepository;
	private final DepartRepository departRepository;
	private final String baseUrl = "https://gitlab.com/api/v4/"; // 기본 public url

	// gitlab 계정 연동 체크
	public GitLabConnectDTO gitConnect(String username, String token, String accessToken) {
		String url = baseUrl + "users?username=";
		url += username;
		GitLabConnectDTO gitLabConnectDto = new GitLabConnectDTO();

		String userId = jwtTokenProvider.getUserId(token);
		User user = userRepository.findByUserId(userId);

		// 헤더에 토큰 담기
		String s = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);
		headers.set("private-token", accessToken);
		HttpEntity entity = new HttpEntity(headers);

		try {
			// api 요청
			ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
			Gson gson = new Gson();
			UserGitLabDTO[] userGitLab = gson.fromJson(responseEntity.getBody(), UserGitLabDTO[].class);
			List<UserGitLabDTO> list = Arrays.asList(userGitLab);

			if (!list.isEmpty()) {
				// DB에 insert 후 true 리턴
				GitLab gitLab = new GitLab();
				UserGitLabDTO gitLabDto = list.get(0);

				gitLab = modelMapper.map(gitLabDto, GitLab.class);
				gitLab.setGitlabId(gitLabDto.getId());
				gitLab.setAvatarUrl(gitLabDto.getAvatar_url());
				gitLab.setWebUrl(gitLabDto.getWeb_url());
				gitLab.setUser(user);
				gitLab.setAccessToken(accessToken);
				gitLabRepository.save(gitLab);
				gitLabConnectDto = modelMapper.map(gitLab, GitLabConnectDTO.class);
				gitLabConnectDto.setAccessflag(true);
				gitLabConnectDto.setFlag(true);
			} else { // username 사용자가 없을 경우
				gitLabConnectDto.setAccessflag(true);
				gitLabConnectDto.setFlag(false);
			}
			return gitLabConnectDto;
		} catch (Exception e) { // header token이 유효하지 않을 경우
			System.out.println(e.getMessage());
			return gitLabConnectDto;
		}
	}

	// gitlab 계정 연동 수정
	public GitLabConnectDTO updateGitConnect(String gitlabId, String username, String token, String accessToken) {

		String url = baseUrl + "users?username=";
		url += username;
		GitLabConnectDTO gitLabConnectDto = new GitLabConnectDTO();

		String userId = jwtTokenProvider.getUserId(token);
		User user = userRepository.findByUserId(userId);
		GitLab gitLab = gitLabRepository.findByGitlabIdAndUser(gitlabId, user);

		// 헤더에 토큰 담기
		String s = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);
		headers.set("private-token", accessToken);
		HttpEntity entity = new HttpEntity(headers);

		try {
			// api 요청
			ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
			Gson gson = new Gson();
			UserGitLabDTO[] userGitLab = gson.fromJson(responseEntity.getBody(), UserGitLabDTO[].class);
			List<UserGitLabDTO> list = Arrays.asList(userGitLab);

			// DB에서 해당 유저를 찾을 수 없을 때
			if (gitLab == null) {
				gitLabConnectDto.setAccessflag(true); //토큰은 통과
				return gitLabConnectDto;
			}
			
			// 해당 유저가 있다면, gitlab 테이블에 update한 후 true 리턴
			if (!list.isEmpty()) {

				// 기존 계정 삭제
				gitLabRepository.delete(gitLab);
				UserGitLabDTO gitLabDto = list.get(0);

				gitLab = modelMapper.map(gitLabDto, GitLab.class);
				gitLab.setGitlabId(gitLabDto.getId());
				gitLab.setAvatarUrl(gitLabDto.getAvatar_url());
				gitLab.setWebUrl(gitLabDto.getWeb_url());
				gitLab.setUser(user);
				gitLab.setAccessToken(accessToken); // 재발급한 토큰도 저장
				gitLabRepository.save(gitLab);
				gitLabConnectDto = modelMapper.map(gitLab, GitLabConnectDTO.class);
				gitLabConnectDto.setAccessflag(true);
				gitLabConnectDto.setFlag(true);

			} else { // username 사용자가 없을 경우
				gitLabConnectDto.setFlag(false);
				gitLabConnectDto.setAccessflag(true);
			}
			return gitLabConnectDto;
		} catch (Exception e) { // header token이 유효하지 않을 경우
			System.out.println(e.getMessage());
			return gitLabConnectDto;
		}
	}

	// gitlab 계정 연결 끊기
	public boolean deleteGitConnect(String gitlabId, String token) {
		String userId = jwtTokenProvider.getUserId(token);
		User user = userRepository.findByUserId(userId);
		GitLab gitLab = gitLabRepository.findByGitlabIdAndUser(gitlabId, user);
		if (gitLab == null) { // 해당 유저를 찾을 수 없을 때
			return false;
		}
		gitLabRepository.delete(gitLab);
		return true;
	}

	// 프로젝트 목록 가져오기 - 토큰 사용
	public GitLabProjectListDTO getProjects(String token, String gitlabId) {
		String url = baseUrl + "/projects?membership=true" + "&per_page=50000";

		GitLab gitLab = gitLabRepository.findById(gitlabId)
				.orElseThrow(() -> new IllegalArgumentException("no gitlab Id in database"));
		String accessToken = gitLab.getAccessToken();

		//목록
		GitLabProjectListDTO gitLabProjectListDto = new GitLabProjectListDTO();
		List<GitLabProjectDTO> verifiedList = new ArrayList<GitLabProjectDTO>();
		List<GitLabProjectDTO> unverifiedList = new ArrayList<GitLabProjectDTO>();

		// 헤더 담음
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);
		headers.set("private-token", accessToken);
		HttpEntity entity = new HttpEntity(headers);
		List<GitLabProjectDTO> gitLabProjectlist = new ArrayList<GitLabProjectDTO>();
		try {
			ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
			Gson gson = new Gson();
			GitLabProjectDTO[] gitLabProjectDto = gson.fromJson(responseEntity.getBody(), GitLabProjectDTO[].class);
			gitLabProjectlist = Arrays.asList(gitLabProjectDto);

			// 검증, 비검증 목록으로 나누기
			for (GitLabProjectDTO gitLabProject : gitLabProjectlist) {
				String projectId = gitLabProject.getId();
				Project project = projectRepository.findByProjectId(projectId);

				if (project == null) {
					unverifiedList.add(gitLabProject);
				} else {
					verifiedList.add(gitLabProject);
				}
			}
			gitLabProjectListDto.setAccessflag(true);
			gitLabProjectListDto.setUnverifiedList(unverifiedList);
			gitLabProjectListDto.setVerifiedList(verifiedList);

			return gitLabProjectListDto;
		} catch (Exception e) {
			// 토큰 유효하지 않는 경우
			gitLabProjectListDto.setUnverifiedList(unverifiedList);
			gitLabProjectListDto.setVerifiedList(verifiedList);
			gitLabProjectListDto.setAccessflag(false);
			return gitLabProjectListDto;
		}
	}

	// 프로젝트 추가하기 - 검증
	public void addProject(String token, List<GitLabProjectDTO> projectList, String gitlabId) {

		String userId = jwtTokenProvider.getUserId(token);
		User user = userRepository.findByUserId(userId);
		Depart depart = user.getDepart();

		// 검증전 추가 - status 검증전
		for (GitLabProjectDTO gitLabProjectDTO : projectList) {
			String projectId = gitLabProjectDTO.getId();
			Project project = new Project();
			project.setUser(user);
			project.setDepart(depart);
			project.setName(gitLabProjectDTO.getName());
			project.setProjectId(projectId);
			projectRepository.save(project);
		}

//		List<AnalyProjectListDTO> analyProjectList = new ArrayList<AnalyProjectListDTO>();
//		// 검증
//		// 프로젝트 tree 가져오기 - 프로젝트별 패키지 매니저 파일 리스트 뽑기 pom.xml찾기
//		for (GitLabProjectDTO gitLabProjectDTO : projectList) {
//			AnalyProjectListDTO analyProjectListDto = new AnalyProjectListDTO();
//			String projectId = gitLabProjectDTO.getId();
//			String projectName = gitLabProjectDTO.getName();
//
//			String url = baseUrl + "projects/" + 26342734 + "/repository/tree?recursive=true&per_page=50000";
//			GitLab gitLab = gitLabRepository.findByGitlabId(gitlabId);
//
//			List<RepositoryTreeDTO> repositoryTreeList = new ArrayList<RepositoryTreeDTO>();
//			RepositoryTreeDTO returnRepositoryDto = new RepositoryTreeDTO();
//
//			String response = restTemplate.getForObject(url, String.class);
//			Gson gson = new Gson();
//			RepositoryTreeDTO[] repositoryTreeDto = gson.fromJson(response, RepositoryTreeDTO[].class);
//			repositoryTreeList = Arrays.asList(repositoryTreeDto);
//			List<RepositoryTreeDTO> packageManageFileList = new ArrayList<RepositoryTreeDTO>();
//			for (RepositoryTreeDTO repositoryTree : repositoryTreeList) {
//				if (repositoryTree.getName().equals("pom.xml")) {
//					returnRepositoryDto = repositoryTree;
//					packageManageFileList.add(returnRepositoryDto);
//				}
//			}
//			analyProjectListDto.setProjectId(projectId);
//			analyProjectListDto.setProjectName(projectName);
//			analyProjectListDto.setPackageManageFileList(packageManageFileList);
//			analyProjectList.add(analyProjectListDto);
//		}

		// url encoding
		// base64
		// 검증
		// 플젝 status 업데이트

	}

	// 재검증 - 검증시간 업데이트 되는지 확인

	// pom.xml불러오기 예외처리, token처리 필요
//	public RepositoryTreeDTO getPackgeManageFile(String token, String projectId, String gitlabId)
//			throws UnsupportedEncodingException {
//		
//		// 프로젝트 tree 가져오기 - pom.xml찾기
//
//		// https://lab.ssafy.com/api/v4/projects/40557/repository/tree?recursive=true&per_page=9999
//
//		String url = baseUrl + "projects/" + projectId + "/repository/tree?recursive=true&per_page=9999";
//		GitLab gitLab = gitLabRepository.findByGitlabId(gitlabId);
//		// String accessToken = gitLab.getAccessToken();
//
//		RepositoryTreeDTO returnRepositoryDto = new RepositoryTreeDTO();
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_XML);
//		// headers.set("private-token", accessToken);
//		HttpEntity entity = new HttpEntity(headers);
//
//		List<RepositoryTreeDTO> repositoryTreeList = new ArrayList<RepositoryTreeDTO>();
//		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
//		Gson gson = new Gson();
//		RepositoryTreeDTO[] repositoryTreeDto = gson.fromJson(responseEntity.getBody(), RepositoryTreeDTO[].class);
//		repositoryTreeList = Arrays.asList(repositoryTreeDto);
//		for (RepositoryTreeDTO repositoryTree : repositoryTreeList) {
//			if (repositoryTree.getName().equals("pom.xml")) {
//				returnRepositoryDto = repositoryTree;
//			}
//		}
//
//		// name , contets
//		return returnRepositoryDto;
//	}

}
