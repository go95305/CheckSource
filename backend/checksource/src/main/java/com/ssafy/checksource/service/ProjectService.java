package com.ssafy.checksource.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.checksource.config.security.JwtTokenProvider;
import com.ssafy.checksource.model.dto.AnalyProjectDTO;
import com.ssafy.checksource.model.dto.LicenseDTO;
import com.ssafy.checksource.model.dto.OpensourcelistDTO;
import com.ssafy.checksource.model.entity.LicenseOpensource;
import com.ssafy.checksource.model.entity.Opensource;
import com.ssafy.checksource.model.repository.LicenseOpensourceRepository;
import com.ssafy.checksource.model.repository.ProjectRepository;
import com.ssafy.checksource.model.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectService {

	private final JwtTokenProvider jwtTokenProvider;
	private final ModelMapper modelMapper = new ModelMapper();
	private final UserRepository userRepository;
	private final ProjectRepository projectRepository;
	private final LicenseOpensourceRepository licenseOpensourceRepository;
	
	
	
	// 부서별 분석된 프로젝트 목록
	public void getProjectListByDepart(Long departId) {
		List<AnalyProjectDTO> list = new ArrayList<AnalyProjectDTO>();
		AnalyProjectDTO analyProjectDto = new AnalyProjectDTO();
		
		
	}

	// 분석된 프로젝트의 오픈소스 목록
	public void getOpensourceListByProject(String projectId) {
		
		List<LicenseOpensource> licenseOpensourceList = new ArrayList<LicenseOpensource>();
		licenseOpensourceList = licenseOpensourceRepository.findAllByProjectId(projectId);
		for (LicenseOpensource licenseOpensource : licenseOpensourceList) {
			OpensourcelistDTO opensourcelistDto = new OpensourcelistDTO();
			Opensource opensouece = licenseOpensource.getOpensource();
			opensourcelistDto = modelMapper.map(opensouece, OpensourcelistDTO.class);
			//오픈소스 - 라이선스 매핑 리스트 어떻게?
		}
	}

	// 분석된 프로젝트의 라이선스 목록
	public void getLicenseListByProject(String projectId) {
		List<LicenseOpensource> licenseOpensourceList = new ArrayList<LicenseOpensource>();
		licenseOpensourceList = licenseOpensourceRepository.findAllByProjectId(projectId);
		
		List<LicenseDTO> licenseList = new ArrayList<LicenseDTO>();
		
		
	}
}
