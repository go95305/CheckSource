package com.ssafy.checksource.controller;


import com.ssafy.checksource.model.dto.*;
import com.ssafy.checksource.service.GithubService;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/git")
@RequiredArgsConstructor
public class GitHubController {

    private final GithubService githubService;

    @ApiOperation(value = "github 계정 연동 ")
    @GetMapping("/githubConnect")
    public GitHubConnectDTO githubConnect(@RequestParam String username, @RequestHeader("TOKEN") String token, @RequestParam String personalAccessToken) {
        return githubService.githubConnect(username, token,personalAccessToken);
    }
    @ApiOperation(value = "github 계정 연동 삭제" )
    @DeleteMapping("/githubConnect")
    public void deleteGitHubConnect(@RequestParam Long githubId, @RequestHeader("TOKEN") String token) {
        System.out.println("hi");
        githubService.deleteGitConnect(githubId, token);
    }
    @ApiOperation(value = "프로젝트 브랜치 리스트 가져오기")
    @GetMapping("/github/branches")
    public List<GithubBranchesDTO> getBranches(@RequestHeader("TOKEN") String token, @RequestParam String username, @RequestParam String repo,@RequestParam Long githubId) {
        return githubService.getBranches(token, username, repo,githubId );
    }

    @ApiOperation(value = "프로젝트 목록 가져오기")
    @GetMapping("/github/projects")
    public GitHubProjectListDTO getProjects(@RequestHeader("TOKEN") String token, @RequestParam Long githubId) {
        System.out.println("hi");
        return githubService.getProjects(token, githubId);
    }

    @ApiOperation(value = "프로젝트 삭제하기")
    @DeleteMapping("/github/projects")
    public boolean deleteProject(@RequestHeader("TOKEN") String token, @RequestParam String projectId, @RequestParam Long githubId) {
        return githubService.deleteProject(token, projectId, githubId);
    }

    @ApiOperation(value = "프로젝트 추가하기-검증 ")
    @PostMapping("/github/projects")
    public boolean addProjects(@RequestHeader("TOKEN") String token, @RequestBody List<GitHubProjectDTO> projectList, @RequestParam Long githubId, @RequestParam String sha){
        try {
            return githubService.addProject(token, projectList, githubId, sha);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
