package com.ssafy.checksource.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.checksource.service.OpensourceService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/opensource")
@RequiredArgsConstructor
public class OpenSourceController {
	// 넣고
	// 이름 검색하고
	// 전체 목록 불러오고
	// 삭제? 하고
	
	private final OpensourceService opensourceService;
	
	@ApiOperation(value = "오픈소스 전체 목록 불러오기")
	@GetMapping("/getAll")
	public Object test() {
		return opensourceService.getAllOpensource();
	}
	
	
}
