package com.ssafy.checksource.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.checksource.config.security.JwtTokenProvider;
import com.ssafy.checksource.model.dto.AnalyProjectDTO;
import com.ssafy.checksource.model.dto.OpensourceDTO;
import com.ssafy.checksource.model.dto.ProjectLiceseListDTO;
import com.ssafy.checksource.model.dto.ProjectListByDepartDTO;
import com.ssafy.checksource.model.entity.Depart;
import com.ssafy.checksource.model.entity.License;
import com.ssafy.checksource.model.entity.LicenseOpensource;
import com.ssafy.checksource.model.entity.Opensource;
import com.ssafy.checksource.model.entity.Project;
import com.ssafy.checksource.model.repository.DepartRepository;
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
	private final DepartRepository departRepository;
	private final LicenseOpensourceRepository licenseOpensourceRepository;
	
	// 부서별 분석된 프로젝트 목록
	public List<ProjectListByDepartDTO> getProjectListByDepart(Long departId) {
		List<ProjectListByDepartDTO> projectListDto = new ArrayList<ProjectListByDepartDTO>();
		Depart dapart = departRepository.findByDepartId(departId);
		List<Project> projectList = projectRepository.findByDepart(dapart);
		
		for (Project project : projectList) {
			ProjectListByDepartDTO projectDto = new ProjectListByDepartDTO();
			projectDto = modelMapper.map(project,ProjectListByDepartDTO.class);
			//프로젝트 id별 오픈소스 리스트
			List<LicenseOpensource> licenseOpensourceList = new ArrayList<LicenseOpensource>();
			licenseOpensourceList = licenseOpensourceRepository.findAllByProjectId(project.getProjectId());
			//수정해야돼 - 라이선스 리스트
			projectDto.setOpensourceCnt(licenseOpensourceList.size());
			projectDto.setLicenseCnt(licenseOpensourceList.size());
			projectListDto.add(projectDto);
			//유저
		}
		return projectListDto;
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

	// 분석된 프로젝트의 라이선스 목록 -> 수정해야해  라이선스가 여러개일 경우 distinct해야함
	public List<ProjectLiceseListDTO> getLicenseListByProject(String projectId) {
		List<LicenseOpensource> licenseOpensourceList = new ArrayList<LicenseOpensource>();
		licenseOpensourceList = licenseOpensourceRepository.findAllByProjectId(projectId);		
		List<ProjectLiceseListDTO> licenseList = licenseOpensourceList.stream().map(ProjectLiceseListDTO::new).collect(Collectors.toList());
		return licenseList;
	}
}
