package com.ssafy.checksource.controller;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.checksource.model.dto.GitLabConnectDTO;
import com.ssafy.checksource.model.dto.GitLabProjectDTO;
import com.ssafy.checksource.model.dto.GitLabProjectListDTO;
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
	@GetMapping("/test/{id}")
	public void test(@PathVariable Long id) {
		
	}
	
	
	@ApiOperation(value = "gitlab 계정 연동 ")
	@GetMapping("/gitlabConnect")
	public GitLabConnectDTO gitConnect(@RequestParam String username, @RequestHeader("TOKEN") String token, @RequestParam Long gitlabId) {
		return gitService.gitConnect(username, token, gitlabId);
	}

	@ApiOperation(value = "gitlab 계정 연동 삭제" )
	@DeleteMapping("/gitlabConnect")
	public void deleteGitConnect(@RequestParam Long gitlabId, @RequestHeader("TOKEN") String token) {
		gitService.deleteGitConnect(gitlabId, token);
	}
	
	
	@ApiOperation(value = "프로젝트 목록 가져오기")
	@GetMapping("/projects")
	public GitLabProjectListDTO getProjects(@RequestHeader("TOKEN") String token, @RequestParam Long gitlabId) {
		return gitService.getProjects(token, gitlabId);
	}
//	
//	@ApiOperation(value = "프로젝트 추가하기-검증 ")
//	@PostMapping("/projects")
//	public boolean addProjects(@RequestHeader("TOKEN") String token, @RequestBody List<GitLabProjectDTO> projectList,@RequestParam String gitlabId) throws URISyntaxException, UnsupportedEncodingException {
//		try {
//			return gitService.addProject(token, projectList, gitlabId);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//	}
//	
//	@ApiOperation(value = "프로젝트 삭제하기", notes = "- 삭제할 projectId와 유저의 jwttoken을 헤더에 담아 보낸다. \n - 프로젝트의 부서와 지우려는 유저의 부서가 일치해야 지워진다. \n - 성공적으로 지워지면 true, 실패시 false 반환 ")
//	@DeleteMapping("/projects")
//	public boolean deleteProject(@RequestHeader("TOKEN") String token, @RequestParam String projectId) {
//		return gitService.deleteProject(token, projectId);
//	}
//	

	
}
