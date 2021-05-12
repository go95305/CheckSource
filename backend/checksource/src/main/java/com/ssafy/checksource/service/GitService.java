package com.ssafy.checksource.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


import com.google.gson.Gson;
import com.ssafy.checksource.config.security.JwtTokenProvider;
import com.ssafy.checksource.model.dto.AnalyProjectListDTO;
import com.ssafy.checksource.model.dto.GitLabConnectDTO;
import com.ssafy.checksource.model.dto.GitLabProjectDTO;
import com.ssafy.checksource.model.dto.GitLabProjectListDTO;
import com.ssafy.checksource.model.dto.PackageManageFileDTO;
import com.ssafy.checksource.model.dto.ProjectBranchesDTO;
import com.ssafy.checksource.model.dto.RepositoryTreeDTO;
import com.ssafy.checksource.model.dto.UserGitLabDTO;
import com.ssafy.checksource.model.dto.UserGitLabTokenDTO;
import com.ssafy.checksource.model.entity.Depart;
import com.ssafy.checksource.model.entity.GitLab;
import com.ssafy.checksource.model.entity.GitLabUser;
import com.ssafy.checksource.model.entity.Project;
import com.ssafy.checksource.model.entity.User;
import com.ssafy.checksource.model.key.GitLabUserKey;
import com.ssafy.checksource.model.repository.DepartRepository;
import com.ssafy.checksource.model.repository.GitLabRepository;
import com.ssafy.checksource.model.repository.GitLabUserRepository;
import com.ssafy.checksource.model.repository.OpensourceRepository;
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
	private final GitLabUserRepository gitLabUserRepository;
	private final AnalyzeService analyzeService;

	// gitlab 계정 연동 체크
	public GitLabConnectDTO gitConnect(String username, String token, Long gitlabId) {
		GitLab gitlab = gitLabRepository.findById(gitlabId)
				.orElseThrow(() -> new IllegalArgumentException("no gitLab data"));
		String baseUrl = gitlab.getBaseUrl();
		String accessToken = gitlab.getRootAccessToken(); // 루트토큰

		String userId = jwtTokenProvider.getUserId(token);
		User user = userRepository.findByUserId(userId);

		String url = baseUrl + "users?username=";
		url += username;
		GitLabConnectDTO gitLabConnectDto = new GitLabConnectDTO();

		// 헤더에 토큰 담기
		String s = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("private-token", accessToken);
		HttpEntity entity = new HttpEntity(headers);

		try {
			// api 요청
			ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
			Gson gson = new Gson();
			UserGitLabDTO[] userGitLab = gson.fromJson(responseEntity.getBody(), UserGitLabDTO[].class);
			List<UserGitLabDTO> list = Arrays.asList(userGitLab);

			if (!list.isEmpty()) {
				UserGitLabDTO gitLabDto = list.get(0);
				// 0. 해당 유저가 이미 연동되어 있는지 확인
				// 1. user_access_token 생성
				// 유저토큰 생성 api 요청
				String secondUrl = baseUrl + "users/" + gitLabDto.getId() + "/impersonation_tokens";
				JSONObject parameters = new JSONObject();
				parameters.put("name", "test");
				JSONArray array = new JSONArray();
				array.put("api");
				parameters.put("scopes", array);
				HttpEntity requestEntity = new HttpEntity(parameters.toString(), headers);
				ResponseEntity<String> response = restTemplate.exchange(secondUrl, HttpMethod.POST, requestEntity,
						String.class);
				UserGitLabTokenDTO userGitlabTokenDto = gson.fromJson(response.getBody(), UserGitLabTokenDTO.class);
				String userGitlabToken = userGitlabTokenDto.getToken();

				// 2. DB에 insert 후 true 리턴
				GitLabUser gitlabUser = new GitLabUser();
				GitLabUserKey gitlabUserKey = new GitLabUserKey();
				gitlabUserKey.setGitlabId(gitlabId);
				gitlabUserKey.setUserId(userId);
				gitlabUser.setGitlabUserKey(gitlabUserKey);
				gitlabUser.setUserGitlabId(gitLabDto.getId());
				gitlabUser.setUsername(gitLabDto.getUsername());
				gitlabUser.setUserAccessToken(userGitlabToken);
				GitLabUser gitlabSaveUser = gitLabUserRepository.save(gitlabUser);
				// set
				gitLabConnectDto.setGitlabId(gitlabId);
				gitLabConnectDto.setUserGitlabId(gitlabSaveUser.getUserGitlabId());
				gitLabConnectDto.setUsername(gitlabSaveUser.getUsername());
				gitLabConnectDto.setFlag(true);
			} else {
				// username 사용자가 없을 경우
				gitLabConnectDto.setFlag(false);
			}
			return gitLabConnectDto;
		} catch (HttpClientErrorException e) {
			// accessToken이 유효하지 않을 경우 -> root계정 토큰 만료기간 제한 없으면 상관없음
			return gitLabConnectDto;
		} catch (JSONException e) {
			// json 매핑 오류
			e.printStackTrace();
			return gitLabConnectDto;
		}
	}

	// gitlab 계정 연결 끊기
	public void deleteGitConnect(Long gitlabId, String token) {
		GitLabUser gitlabUser = new GitLabUser();
		GitLabUserKey gitlabUserKey = new GitLabUserKey();
		String userId = jwtTokenProvider.getUserId(token);
		gitLabRepository.findById(gitlabId).orElseThrow(() -> new IllegalArgumentException("no gitlab data"));
		gitlabUserKey.setGitlabId(gitlabId);
		gitlabUserKey.setUserId(userId);
		gitlabUser.setGitlabUserKey(gitlabUserKey);
		gitLabUserRepository.delete(gitlabUser);
	}

	// 프로젝트 목록 가져오기 - 유저 토큰 사용
	public GitLabProjectListDTO getProjects(String token, Long gitlabId) {
		// 목록
		GitLabProjectListDTO returnDto = new GitLabProjectListDTO();
		List<GitLabProjectDTO> gitLabProjectList = new ArrayList<GitLabProjectDTO>();

		String userId = jwtTokenProvider.getUserId(token);
		User user = userRepository.findByUserId(userId);
		GitLab gitlab = gitLabRepository.findById(gitlabId)
				.orElseThrow(() -> new IllegalArgumentException("no gitLab data"));
		GitLabUser gitlabUser = new GitLabUser();
		GitLabUserKey gitlabUserKey = new GitLabUserKey();

		gitlabUserKey.setGitlabId(gitlabId);
		gitlabUserKey.setUserId(userId);
		gitlabUser.setGitlabUserKey(gitlabUserKey);
		String userAccessToken = gitLabUserRepository.findByUserAndGitlab(user, gitlab).getUserAccessToken();

		String baseUrl = gitlab.getBaseUrl();
		String url = baseUrl + "/projects?membership=true" + "&per_page=50000";

		// 헤더 담음
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("private-token", userAccessToken);
		HttpEntity entity = new HttpEntity(headers);
		List<GitLabProjectDTO> gitLabProjectlist = new ArrayList<GitLabProjectDTO>();
		try {
			ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
			Gson gson = new Gson();
			GitLabProjectDTO[] gitLabProjectDto = gson.fromJson(responseEntity.getBody(), GitLabProjectDTO[].class);
			gitLabProjectlist = Arrays.asList(gitLabProjectDto);

			// 검증, 비검증으로 나누기
			for (GitLabProjectDTO gitLabProject : gitLabProjectlist) {
				String projectId = gitLabProject.getId();
				Project project = projectRepository.findByProjectId(projectId);

				if (project == null) {// 비검증
					gitLabProjectList.add(gitLabProject);
				} else {// 검증
					gitLabProject.setVerified(true);
					gitLabProjectList.add(gitLabProject);
				}
			}
			returnDto.setAccessflag(true);
			returnDto.setProjectList(gitLabProjectList);
			return returnDto;
		} catch (Exception e) {
			// 유저 토큰 유효하지 않는 경우 - 깃랩 연동 다시 시키기
			returnDto.setAccessflag(false);
			returnDto.setProjectList(gitLabProjectList);
			return returnDto;
		}
	}

	// 프로젝트 삭제하기
	public boolean deleteProject(String token, String projectId) {
		String userId = jwtTokenProvider.getUserId(token);
		User user = userRepository.findByUserId(userId);
		Project project = projectRepository.findByProjectId(projectId);
		if (user.getDepart().getDepartId() == project.getDepart().getDepartId()) {
			projectRepository.delete(project);
			return true;
		}
		return false;
	}

	// 프로젝트 브런치 가져오기
	public List<ProjectBranchesDTO> getBranches(String token, String projectId, Long gitlabId) {
		GitLab gitlab = gitLabRepository.findById(gitlabId)
				.orElseThrow(() -> new IllegalArgumentException("no gitLab data"));
		String baseUrl = gitlab.getBaseUrl();
		String accessToken = gitlab.getRootAccessToken(); // 루트토큰
		String url = baseUrl + "/projects/" + projectId + "/repository/branches";
		List<ProjectBranchesDTO> projectBranchesList = new ArrayList<ProjectBranchesDTO>();
		// 헤더 담음
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("private-token", accessToken);
		HttpEntity entity = new HttpEntity(headers);

		try {
			// api 요청
			ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
			Gson gson = new Gson();
			ProjectBranchesDTO[] branchList = gson.fromJson(responseEntity.getBody(), ProjectBranchesDTO[].class);
			projectBranchesList = Arrays.asList(branchList);
			System.out.println(projectBranchesList.toString());
			
		} catch (HttpClientErrorException e) {
			// accessToken이 유효하지 않을 경우 -> root계정 토큰 만료기간 제한 없으면 상관없음
			// 잘못된 프로젝트 id 적을 경우
			return projectBranchesList;
		}
		return projectBranchesList;
	}

//	// 프로젝트 추가하기 - 검증
//	public boolean addProject(String token, List<GitLabProjectDTO> projectList, String gitlabId) throws Exception {
//
//		String userId = jwtTokenProvider.getUserId(token);
//		User user = userRepository.findByUserId(userId);
//		Depart depart = user.getDepart();
//
//		// 검증전 프로젝트 추가 - status 검증전 세팅 flase
//		for (GitLabProjectDTO gitLabProjectDTO : projectList) {
//			String projectId = gitLabProjectDTO.getId();
//			Project project = new Project();
//			project.setUser(user);
//			project.setDepart(depart);
//			project.setName(gitLabProjectDTO.getName());
//			project.setProjectId(projectId);
//			projectRepository.save(project);
//		}
//
//		//검증할 프로젝트 리스트
//		List<AnalyProjectListDTO> analyProjectList = new ArrayList<AnalyProjectListDTO>();
//		GitLab gitLab = gitLabRepository.findById(gitlabId).orElseThrow(() -> new IllegalArgumentException("no gitlab Id in database"));
//		String accessToken = gitLab.getAccessToken();
//		
//		// 프로젝트별 검증 
//		for (GitLabProjectDTO gitLabProjectDTO : projectList) {
//			AnalyProjectListDTO analyProjectListDto = new AnalyProjectListDTO();
//			String projectId = gitLabProjectDTO.getId();
//			String projectName = gitLabProjectDTO.getName();
//
//			//1. repositoryTree 전체 리스트로 가져오기 - 브런치 받아야함 현재는 master을 기본으로
//			String url = baseUrl + "projects/" + projectId + "/repository/tree?ref=master&recursive=true&per_page=50000";
//			
//			
//			List<RepositoryTreeDTO> repositoryTreeList = new ArrayList<RepositoryTreeDTO>();
//			RepositoryTreeDTO returnRepositoryDto = new RepositoryTreeDTO();
//			
//			// 헤더 담음
//			HttpHeaders headers = new HttpHeaders();
//			headers.setContentType(MediaType.APPLICATION_XML);
//			headers.set("private-token", accessToken);
//			HttpEntity entity = new HttpEntity(headers);		
//			try {//api - RepositoryTree 리스트 받아옴
//				ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
//				Gson gson = new Gson();
//				RepositoryTreeDTO[] repositoryTreeDto = gson.fromJson(responseEntity.getBody(), RepositoryTreeDTO[].class);
//				repositoryTreeList = Arrays.asList(repositoryTreeDto);
//			}catch (HttpClientErrorException e) {
//				//토큰이 유효하지 않을 경우 401
//				if(e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
//					return false;
//				}
//				//repository tree가 없을 경우 404
//				if(e.getStatusCode() == HttpStatus.NOT_FOUND) {
//					//검증할 필요가 없으므로 검증상태 처리 true
//					Project project = projectRepository.findByProjectId(projectId);
//					project.setStatus(true);
//					projectRepository.save(project);
//					continue;
//				}
//					
//			}
//			
//			//2. repositoryTreeList에서 패키지 매니저 파일 리스트 뽑음
//			List<RepositoryTreeDTO> packageManageFileList = new ArrayList<RepositoryTreeDTO>();
//			for (RepositoryTreeDTO repositoryTree : repositoryTreeList) {
//				if (repositoryTree.getName().equals("pom.xml")) {
//					returnRepositoryDto = repositoryTree;
//					packageManageFileList.add(returnRepositoryDto);
//				}
//			}
//			
//			//set
//			analyProjectListDto.setProjectId(projectId);
//			analyProjectListDto.setProjectName(projectName);
//			analyProjectListDto.setPackageManageFileList(packageManageFileList); //패키지매니저파일 리스트
//			analyProjectList.add(analyProjectListDto);
//		}//end project for문
//
//		//System.out.println(analyProjectList);
//		
//		//3. 프로젝트별 패키지매니저 파일 리스트에서 패키지 매니터 파일의 contents 뽑기
//		for (AnalyProjectListDTO analyProjectListDto : analyProjectList) {
//			List<RepositoryTreeDTO> packageManageFileList = analyProjectListDto.getPackageManageFileList();
//			String projectId = analyProjectListDto.getProjectId();
//
//			//프로젝트별 패키지파일 리스트
//			for (RepositoryTreeDTO packageManageFile : packageManageFileList) {
//				String path = packageManageFile.getPath();
//				
//				//path 경로 url encoding해서 api 요청
//				URI uri = UriComponentsBuilder
//				        .fromUriString(baseUrl+"projects/{projectid}/repository/files/{urlEncoding}?ref=master")
//				        .encode()
//				        .buildAndExpand(projectId, path).toUri();
//				//헤더
//				HttpHeaders headers = new HttpHeaders();
//				headers.setContentType(MediaType.APPLICATION_XML);
//				headers.set("private-token", accessToken);
//				HttpEntity entity = new HttpEntity(headers);	
//				try {
//					ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
//					Gson gson = new Gson();
//					PackageManageFileDTO packageManageFileDto =  gson.fromJson(responseEntity.getBody(), PackageManageFileDTO.class);
//					//contents 뽑음
//					String contents = packageManageFileDto.getContent();
//					String filePath = packageManageFileDto.getFile_path();
//					String fileName = packageManageFileDto.getFile_name();
//					// 4. base64 - decoding 등 승환 코드
//					analyzeService.analyze(projectId, fileName, contents, filePath);
//							
//				} catch (HttpClientErrorException e) {
//					//토큰이 유효하지 않을 경우 401
//					if(e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
//						return false;
//					}
//				}
//			}
//			
//			//5. 검증 완료한 프로젝트 status 업데이트
//			Project project = projectRepository.findByProjectId(projectId);
//			project.setStatus(true);
//			projectRepository.save(project);
//		}
//
//		return true;
//	}

	// 재검증
}
