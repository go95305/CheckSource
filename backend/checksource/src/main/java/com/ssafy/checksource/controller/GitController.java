package com.ssafy.checksource.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.checksource.model.dto.GitLabConnectDTO;
import com.ssafy.checksource.model.dto.GitLabProjectDTO;
import com.ssafy.checksource.model.dto.RepositoryTreeDTO;
import com.ssafy.checksource.service.GitService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/git")
@RequiredArgsConstructor
public class GitController {
	
	private final GitService gitService;
	
	@ApiOperation(value = "테스트")
	@GetMapping("/test")
	public void test() {
		//gitService.getProjects();
	}
	
	
	@ApiOperation(value = "gitlab 연동 체크 ")
	@GetMapping("/gitlabConnect")
	public GitLabConnectDTO gitConnect(@RequestParam String userName, @RequestHeader("TOKEN") String token, @RequestParam String AccessToken) {
		return gitService.gitConnect(userName, token, AccessToken);
	}
	
	
	@ApiOperation(value = "프로젝트 목록 가져오기 ")
	@GetMapping("/projects")
	public List<GitLabProjectDTO> getProjects(@RequestHeader("TOKEN") String token, @RequestParam String gitId) {
		return gitService.getProjects(token, gitId);
	}
	
	//프로젝트 pom.xml 불러오기
	@ApiOperation(value = "프로젝트 패키지매니저 파일 가져오기 ")
	@GetMapping("/packageManager")
	public RepositoryTreeDTO getPackgeManageFile(@RequestHeader("TOKEN") String token, @RequestParam String projectId, @RequestParam String gitId) throws UnsupportedEncodingException {
		return gitService.getPackgeManageFile(token, projectId, gitId);
	}
	
	
	//프로젝트 추가하기
	
	
	
}
