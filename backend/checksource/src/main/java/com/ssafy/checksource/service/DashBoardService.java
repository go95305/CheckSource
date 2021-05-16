package com.ssafy.checksource.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.checksource.config.security.JwtTokenProvider;
import com.ssafy.checksource.model.repository.DepartRepository;
import com.ssafy.checksource.model.repository.LicenseOpensourceRepository;
import com.ssafy.checksource.model.repository.LicenseRepository;
import com.ssafy.checksource.model.repository.OpensourceProjectRepository;
import com.ssafy.checksource.model.repository.ProjectRepository;
import com.ssafy.checksource.model.repository.UnmappedOpensourceRepository;
import com.ssafy.checksource.model.repository.UserRepository;
import com.ssafy.checksource.model.dto.*;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class DashBoardService {

	
	private final JwtTokenProvider jwtTokenProvider;
	private final ModelMapper modelMapper = new ModelMapper();
	private final UserRepository userRepository;
	private final ProjectRepository projectRepository;
	
	// 부서벌 top 오픈소스 5
	
	// 부서별 라이선스 의무 warning
	public void getlicenseWarning () {
		//페이징 필요, 각각의 프로젝트마다 의무 워닝수 필요
		//최신순 프로젝트 뽑아 
		//프로젝트 당 라이선스 의무워닝 세 
	}
	
	// 부서별 통계 수치
	
	
	
	
}
