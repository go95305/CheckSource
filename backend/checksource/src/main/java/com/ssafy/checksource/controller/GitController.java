package com.ssafy.checksource.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	@GetMapping("/test")
	public void test() {
		
		//gitService.getProjects();
	}
	
	
	@ApiOperation(value = "gitlab 계정 연동 ",  notes = "- 연동할 gitlab의 username과 유저의 jwttoken을 헤더에 담아 보낸다. \n - git연동 성공시 flag = true, gitlabId와 gitlab username 반환 \n - 해당 유저가 없을 경우 flag = false \n - 현재 유저당 gitlabId를 여러개 연동할 수 있으므로 테스트로 여러 id입력하면 db에 그대로 들어감. 주의할 것 \n - 테스트는 ekgml3765 계정으로 연동하기 \n git연동후 localstorage에 gitlabId와 username을 담는다.")
	@GetMapping("/gitlabConnect")
	public GitLabConnectDTO gitConnect(@RequestParam String username, @RequestHeader("TOKEN") String token) {
		return gitService.gitConnect(username, token);
	}
	
	@ApiOperation(value = "gitlab 계정 연동 수정 ",  notes ="- 기존 깃랩 계정의 gitlabId, 변경할 깃랩계정의 username, 해당유저의 jwtToken을 담아 보낸다. \n - git계정 수정 성공시 flag = true, 수정한 gitlabId와 git username반환 \n - 해당 username의 git계정이 없거나, 현재 gitlabId와 매핑되는 유저 정보가 없을 경우 flag = flase 반환 \n- 새로운 git 계정 연동 후 localstorage에 gitlabId와 username을 담는다.")
	@GetMapping("/updateGitlabConnect")
	public GitLabConnectDTO updateGitConnect(@RequestParam String gitlabId, @RequestParam String username, @RequestHeader("TOKEN") String token) {
		return gitService.updateGitConnect(gitlabId, username, token);
	}
	
	@ApiOperation(value = "gitlab 계정 연동 삭제", notes = "- 기존 깃랩 계정의 gitlabId, 해당유저의 jwtToken을 담아 보낸다. \n -git 계정이 성공적으로 삭제시 flag = true를 반환한다.\n -해당 gitlabId와 유저가 매핑되지 않는 정보일 경우 flag = false를 반환한다. \n" )
	@DeleteMapping("/deleteGitlabConnect")
	public boolean deleteGitConnect(@RequestParam String gitlabId, @RequestHeader("TOKEN") String token) {
		return gitService.deleteGitConnect(gitlabId, token);
	}
	
	
	@ApiOperation(value = "프로젝트 목록 가져오기")
	@GetMapping("/projects")
	public GitLabProjectListDTO getProjects(@RequestHeader("TOKEN") String token, @RequestParam String gitlabId) {
		return gitService.getProjects(token, gitlabId);
	}
	
	@ApiOperation(value = "프로젝트 추가하기-검증 ")
	@PostMapping("/addProjects")
	public void addProjects(@RequestHeader("TOKEN") String token, @RequestBody List<GitLabProjectDTO> projectList,@RequestParam String gitlabId) {
		gitService.addProject(token, projectList, gitlabId);
	}
	

	
}