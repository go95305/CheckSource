package com.ssafy.checksource.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.checksource.model.dto.AnalyzeDTO;
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
	
	
//	@ApiOperation(value = "분석된 프로젝트의 오픈소스 목록")
//	@GetMapping("/opensourceList")
//	public void getOpensourceListByProject(@RequestHeader("TOKEN") String token, @RequestParam String projectId) {
//		//projectService.getOpensourceListByProject(projectId);
//	}
//	
//	@ApiOperation(value = "분석된 프로젝트의 라이선스 목록")
//	@GetMapping("/opensourceList")
//	public void getLicenseListByProject(@RequestHeader("TOKEN") String token, @RequestParam String projectId) {
//		//projectService.getLicenseListByProject(projectId);
//	}
	


}
