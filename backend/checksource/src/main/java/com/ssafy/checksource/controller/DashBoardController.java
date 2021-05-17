package com.ssafy.checksource.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ssafy.checksource.model.dto.*;
import com.ssafy.checksource.service.DashBoardService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashBoardController {
	
	private final DashBoardService dashBoardService;
	
	@ApiOperation(value = "전체 통계 정보 가져오기")
	@GetMapping("/statistics")
	public StatisticsTotalDTO getTotalStatistics (@RequestHeader("TOKEN") String token) {
		return dashBoardService.getTotalStatistics();
	}
	
	@ApiOperation(value = "부서별 통계 정보 가져오기")
	@GetMapping("/statistics/{departId}")
	public StatisticsByDepartDTO getStatisticsByDepart (@RequestHeader("TOKEN") String token, @PathVariable Long departId) {
		return dashBoardService.getStatisticsByDepart(departId);
	}
	
	@ApiOperation(value = "부서별 오픈소스 top5")
	@GetMapping("/top5/{departId}")
	public List<Top5OpensourceDTO> getTop5OpensourceByDepart(@RequestHeader("TOKEN") String token, @PathVariable Long departId) {
		return dashBoardService.getTop5OpensourceByDepart(departId);
	}
	
	@ApiOperation(value = "전체  오픈소스 top5")
	@GetMapping("/top5")
	public List<Top5OpensourceDTO> getTotalTop5Opensource(@RequestHeader("TOKEN") String token) {
		return dashBoardService.getTotalTop5Opensource();
	}
	
}
