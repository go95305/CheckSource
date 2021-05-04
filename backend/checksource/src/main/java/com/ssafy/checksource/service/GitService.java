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
import com.ssafy.checksource.model.dto.GitLabConnectDTO;
import com.ssafy.checksource.model.dto.GitLabProjectDTO;
import com.ssafy.checksource.model.dto.RepositoryTreeDTO;
import com.ssafy.checksource.model.dto.UserGitLabDTO;
import com.ssafy.checksource.model.entity.GitLab;
import com.ssafy.checksource.model.entity.User;
import com.ssafy.checksource.model.repository.GitLabRepository;
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
	private final String baseUrl = "https://lab.ssafy.com/api/v4/"; // 기본 테스트 url -> 나중에 신한은행 url로 변경

	// gitlab 계정 연동 체크
	public GitLabConnectDTO gitConnect(String userName, String token, String accessToken) {
		String url = baseUrl + "users?username=";
		url += userName;
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);
		headers.set("private-token", accessToken);
		HttpEntity entity = new HttpEntity(headers);
		GitLabConnectDTO gitLabConnectDto = new GitLabConnectDTO();
		try {

			ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
			Gson gson = new Gson();
			UserGitLabDTO[] userGitLab = gson.fromJson(responseEntity.getBody(), UserGitLabDTO[].class);
			List<UserGitLabDTO> list = Arrays.asList(userGitLab);
			if(list.size() != 0) {
				//DB에 insert 후 true 리턴
				String userId = jwtTokenProvider.getUserId(token);
				User user = userRepository.findByUserId(userId);
				GitLab gitLab = new GitLab();
				UserGitLabDTO gitLabDto = list.get(0);
				
				gitLab = modelMapper.map(gitLabDto,GitLab.class);
				gitLab.setGitlabId(gitLabDto.getId());
				gitLab.setAvatarUrl(gitLabDto.getAvatar_url());
				gitLab.setWebUrl(gitLabDto.getWeb_url());
				gitLab.setUser(user);
				gitLab.setAccessToken(accessToken);
				gitLabRepository.save(gitLab);
				gitLabConnectDto = modelMapper.map(gitLab, GitLabConnectDTO.class);
				gitLabConnectDto.setCheckAccessToken(true);
				gitLabConnectDto.setCheckId(true);
				
				
				return gitLabConnectDto;
			}else {
				gitLabConnectDto.setCheckAccessToken(true);
				gitLabConnectDto.setCheckId(false);
				return gitLabConnectDto;
			}		
		} catch (Exception e) {
			//header token이 유효하지 않을 경우
			System.out.println(e.getMessage());
			return gitLabConnectDto;
		}	
		
	}

	//gitlab 계정 연결 끊기 -> db에서 삭제
	
	//gitlab 계정 연동 수정
	

	// 프로젝트 목록 가져오기
	public List<GitLabProjectDTO> getProjects(String token, String gitId) {
		//String userId = jwtTokenProvider.getUserId(token);
		//User user = userRepository.findByUserId(userId);
		
		String url = baseUrl + "users/" + gitId+"/projects";
		
		GitLab gitLab =  gitLabRepository.findByGitlabId(gitId);
		String accessToken = gitLab.getAccessToken();
	
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
			return gitLabProjectlist;
		} catch (Exception e) { //토큰 유효하지 않는 경우
			//처리 필요
			return gitLabProjectlist;
		}	
	}

	//프로젝트 추가하기
	public void addProject(String projectId) {
		
	}
	
	//pom.xml불러오기 예외처리, token처리 필요
	public RepositoryTreeDTO getPackgeManageFile(String token, String projectId, String gitId) throws UnsupportedEncodingException {
		// 프로젝트 tree 가져오기 - pom.xml찾기

		//https://lab.ssafy.com/api/v4/projects/40557/repository/tree?recursive=true&per_page=9999
		
		String url = baseUrl + "projects/" + projectId+"/repository/tree?recursive=true&per_page=9999";
		GitLab gitLab =  gitLabRepository.findByGitlabId(gitId);
		String accessToken = gitLab.getAccessToken();
		
		RepositoryTreeDTO returnRepositoryDto = new RepositoryTreeDTO();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);
		headers.set("private-token", accessToken);
		HttpEntity entity = new HttpEntity(headers);
		
		List<RepositoryTreeDTO> repositoryTreeList = new ArrayList<RepositoryTreeDTO>();
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		Gson gson = new Gson();
		RepositoryTreeDTO[] repositoryTreeDto = gson.fromJson(responseEntity.getBody(), RepositoryTreeDTO[].class);
		repositoryTreeList = Arrays.asList(repositoryTreeDto);
		for (RepositoryTreeDTO repositoryTree : repositoryTreeList) {
			if(repositoryTree.getName().equals("pom.xml")) {
				returnRepositoryDto = repositoryTree;
			}			
		}
		
		//name , contets
		return returnRepositoryDto;
	}
	
	public void test(String token, String projectId, String gitId) throws UnsupportedEncodingException{
		RepositoryTreeDTO returnRepositoryDto = getPackgeManageFile(token, projectId, gitId);
		
		String path = returnRepositoryDto.getPath();
		System.out.println(path);
//		//String encodingUrl = URLEncoder.encode(path, "UTF-8")
//				.replace(".", "%2E");
//		System.out.println(encodingUrl);
		String packageManageUrl = baseUrl +"projects/"+ projectId + "/repository/files/" + path +"?ref=develop";
		System.out.println(packageManageUrl);
		
		GitLab gitLab =  gitLabRepository.findByGitlabId(gitId);
		String accessToken = gitLab.getAccessToken();
	
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);
		headers.set("private-token", accessToken);
		HttpEntity entity = new HttpEntity(headers);
		ResponseEntity<String> responseEntity2 = restTemplate.exchange(packageManageUrl, HttpMethod.GET, entity, String.class);
		System.out.println(responseEntity2.getBody());
	}

}
