package com.ssafy.checksource.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.checksource.model.dto.AnalyOpensourceListDTO;
import com.ssafy.checksource.model.dto.ProjectLiceseListDTO;
import com.ssafy.checksource.model.dto.ProjectListByDepartDTO;
import com.ssafy.checksource.service.AnalyzeService;
import com.ssafy.checksource.service.ProjectService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/analyze")
@RequiredArgsConstructor
public class AnalyzeController {

	private final AnalyzeService analyzeService;
	private final ProjectService projectService;
	
	@ApiOperation(value = "프로젝트 이름 가져오기")
	@GetMapping("/projectName")
	public String getProjectName (@RequestHeader("TOKEN") String token, @RequestParam String projectId) {
		return projectService.getProjectName(projectId);
	}
	
	@ApiOperation(value = "부서별 분석된 프로젝트 목록")
	@GetMapping("/projectList")
	public List<ProjectListByDepartDTO> getProjectListByDepart(@RequestHeader("TOKEN") String token, @RequestParam Long departId) {
		return projectService.getProjectListByDepart(departId);
	}
	
	@ApiOperation(value = "분석된 프로젝트의 오픈소스 목록")
	@GetMapping("/opensourceList")
	public AnalyOpensourceListDTO getOpensourceListByProject(@RequestHeader("TOKEN") String token, @RequestParam String projectId) {
		return projectService.getOpensourceListByProject(projectId);
	}
	
	@ApiOperation(value = "분석된 프로젝트의 라이선스 목록")
	@GetMapping("/licenseList")
	public List<ProjectLiceseListDTO> getLicenseListByProject(@RequestHeader("TOKEN") String token, @RequestParam String projectId) {
		return projectService.getLicenseListByProject(projectId);
	}
	
	@ApiOperation(value = "test")
	@GetMapping("/test")
	public void test(@RequestParam String projectId,@RequestParam String fileName,@RequestParam String content,@RequestParam String filePath) {
		try {
			analyzeService.analyze(projectId, fileName, content, filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
