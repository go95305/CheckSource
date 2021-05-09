package com.ssafy.checksource.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.checksource.model.dto.OpensourceDTO;
import com.ssafy.checksource.model.dto.OpensourcesaveDTO;
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
	
	@ApiOperation(value = "오픈소스 하나의 상세정보 불러오기")
	@GetMapping("/getDetail/{opensourceId}")
	public OpensourceDTO getDetail(long opensourceId) {
		return opensourceService.getDetailOpensource(opensourceId);
	}
	
	@ApiOperation(value = "오픈소스 저장하기")
	@PostMapping("/addlicense")
	public void insert(@RequestBody OpensourcesaveDTO opsDto) {
		opensourceService.save(opsDto);
	}
	
}
