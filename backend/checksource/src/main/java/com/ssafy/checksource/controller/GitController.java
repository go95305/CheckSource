package com.ssafy.checksource.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		gitService.gitConnect();
	}
	
}
