package com.ssafy.checksource.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.checksource.config.security.JwtTokenProvider;
import com.ssafy.checksource.model.dto.AnalyProjectDTO;
import com.ssafy.checksource.model.dto.LicenseDTO;
import com.ssafy.checksource.model.dto.OpensourceDTO;
import com.ssafy.checksource.model.dto.ProjectLiceseListDTO;
import com.ssafy.checksource.model.entity.License;
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
	public List<OpensourceDTO> getOpensourceListByProject(String projectId) {
		
		List<LicenseOpensource> licenseOpensourceList = new ArrayList<LicenseOpensource>();
		licenseOpensourceList = licenseOpensourceRepository.findAllByProjectId(projectId);
		List<OpensourceDTO> opensourceList = new ArrayList<OpensourceDTO>();
		//수정해야함
		for (LicenseOpensource licenseOpensource : licenseOpensourceList) {
			OpensourceDTO opensourceDto = new OpensourceDTO();
			Opensource opensouece = licenseOpensource.getOpensource();
			opensourceDto = modelMapper.map(opensouece, OpensourceDTO.class);
			
			List<LicenseOpensource> list = opensouece.getLicenses();
			List<String> licenseNameList = new ArrayList<String>();
			for (LicenseOpensource licenseopensource : list) {
				License license = licenseopensource.getLicense();
				licenseNameList.add(license.getName());
			}
			opensourceDto.setLicenseNameList(licenseNameList);
			opensourceList.add(opensourceDto);
				
		}
		return opensourceList;
	}

	// 분석된 프로젝트의 라이선스 목록
	public List<ProjectLiceseListDTO> getLicenseListByProject(String projectId) {
		List<LicenseOpensource> licenseOpensourceList = new ArrayList<LicenseOpensource>();
		licenseOpensourceList = licenseOpensourceRepository.findAllByProjectId(projectId);		
		List<ProjectLiceseListDTO> licenseList = licenseOpensourceList.stream().map(ProjectLiceseListDTO::new).collect(Collectors.toList());
		return licenseList;
	}
}
