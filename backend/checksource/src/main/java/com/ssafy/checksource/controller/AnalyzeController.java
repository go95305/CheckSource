package com.ssafy.checksource.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.checksource.model.dto.AnalyLicenseListDTO;
import com.ssafy.checksource.model.dto.AnalyMappedOpensouceListDTO;
import com.ssafy.checksource.model.dto.AnalyProjectListByDepartDTO;
import com.ssafy.checksource.model.dto.AnalyUnmappedOpensouceListDTO;
import com.ssafy.checksource.model.dto.ProjectInfoDTO;
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
	
	@ApiOperation(value = "프로젝트 정보 가져오기")
	@GetMapping("/projectInfo")
	public ProjectInfoDTO getProjectName (@RequestHeader("TOKEN") String token, @RequestParam String projectId, @RequestParam Long gitType) {
		return projectService.getProjectName(projectId, gitType);
	}
	
	@ApiOperation(value = "부서별 분석된 프로젝트 목록")
	@GetMapping("/projectList")
	public AnalyProjectListByDepartDTO getProjectListByDepart(@RequestHeader("TOKEN") String token, @RequestParam Long departId, @RequestParam int currentPage, @RequestParam int size, @RequestParam String time ) {
		return projectService.getProjectListByDepart(departId, currentPage, size, time);
	}
	
	@ApiOperation(value = "분석된 프로젝트의 매핑된 오픈소스 목록")
	@GetMapping("/mappedOpensourceList")
	public AnalyMappedOpensouceListDTO getMappedOpensourceListByProject (@RequestHeader("TOKEN") String token, @RequestParam String projectId, @RequestParam Long gitType, @RequestParam int size, @RequestParam int currentPage) {
		return projectService.getMappedOpensourceListByProject(projectId, gitType, size, currentPage);
	}
	
	@ApiOperation(value = "분석된 프로젝트의 매핑되지 않은 오픈소스 목록")
	@GetMapping("/unMappedOpensourceList")
	public AnalyUnmappedOpensouceListDTO getUnmappedOpensourceListByProject(@RequestHeader("TOKEN") String token, @RequestParam String projectId, @RequestParam Long gitType, @RequestParam int size, @RequestParam int currentPage) {
		return projectService.getUnmappedOpensourceListByProject(projectId, gitType, size, currentPage);
	}
	
	
	@ApiOperation(value = "분석된 프로젝트의 라이선스 목록")
	@GetMapping("/licenseList")
	public AnalyLicenseListDTO getLicenseListByProject(@RequestHeader("TOKEN") String token, @RequestParam String projectId, @RequestParam Long gitType, @RequestParam int size, @RequestParam int currentPage) {
		return projectService.getLicenseListByProject(projectId, gitType, size, currentPage);
	}

}
