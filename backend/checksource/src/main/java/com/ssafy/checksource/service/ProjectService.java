package com.ssafy.checksource.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.checksource.config.security.JwtTokenProvider;
import com.ssafy.checksource.model.dto.AnalyLicenseListDTO;
import com.ssafy.checksource.model.dto.AnalyMappedOpensouceListDTO;
import com.ssafy.checksource.model.dto.AnalyProjectListByDepartDTO;
import com.ssafy.checksource.model.dto.AnalyUnmappedOpensouceListDTO;
import com.ssafy.checksource.model.dto.OpensourceDTO;
import com.ssafy.checksource.model.dto.ProjectInfoDTO;
import com.ssafy.checksource.model.dto.ProjectLiceseListDTO;
import com.ssafy.checksource.model.dto.ProjectListByDepartDTO;
import com.ssafy.checksource.model.dto.UnmappendOpensourceDTO;
import com.ssafy.checksource.model.entity.Depart;
import com.ssafy.checksource.model.entity.License;
import com.ssafy.checksource.model.entity.LicenseOpensource;
import com.ssafy.checksource.model.entity.OpensourceProject;
import com.ssafy.checksource.model.entity.Project;
import com.ssafy.checksource.model.entity.UnmappedOpensource;
import com.ssafy.checksource.model.repository.DepartRepository;
import com.ssafy.checksource.model.repository.LicenseOpensourceRepository;
import com.ssafy.checksource.model.repository.LicenseRepository;
import com.ssafy.checksource.model.repository.OpensourceProjectRepository;
import com.ssafy.checksource.model.repository.ProjectRepository;
import com.ssafy.checksource.model.repository.UnmappedOpensourceRepository;
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
	private final LicenseRepository licenseRepository;
	private final OpensourceProjectRepository opensourceProjectRepository;
	private final UnmappedOpensourceRepository unmappedOpensourceRepository;
	
	//summary
	
	
	
	//프로젝트 이름
	public ProjectInfoDTO getProjectName(String gitProjectId, Long gitType) {
		ProjectInfoDTO projectInfoDto = new ProjectInfoDTO();
		Project project = projectRepository.findByGitProjectIdAndGitType(gitProjectId, gitType);
		projectInfoDto = modelMapper.map(project, ProjectInfoDTO.class);
		return projectInfoDto;
	}
	
	// 부서별 분석된 프로젝트 목록
	public AnalyProjectListByDepartDTO  getProjectListByDepart(Long departId, int currentPage, int size, String time) {
		AnalyProjectListByDepartDTO analyProjectByDepartDto = new AnalyProjectListByDepartDTO();
		
		List<ProjectListByDepartDTO> projectListDto = new ArrayList<ProjectListByDepartDTO>();
		PageRequest pageRequest = PageRequest.of(currentPage - 1, size);
		Page<Project> projectList = projectRepository.findByDepart(departId, pageRequest, time);
		
		for (Project project : projectList.getContent()) {
			ProjectListByDepartDTO projectDto = new ProjectListByDepartDTO();
			projectDto = modelMapper.map(project, ProjectListByDepartDTO.class);
			//프로젝트 id별 매핑된 오픈소스 갯수 
			List<OpensourceProject> opensourceList = new ArrayList<OpensourceProject>();
			opensourceList = opensourceProjectRepository.findAllByProject(project);
			projectDto.setOpensourceCnt(opensourceList.size());
			//프로젝트 id별 매핑된 라이선스 갯수
			List<LicenseOpensource> licenseOpensourceList = new ArrayList<LicenseOpensource>();
			licenseOpensourceList = licenseOpensourceRepository.findAllByProjectId(project.getProjectId());
			List<ProjectLiceseListDTO> licenseList = licenseOpensourceList.stream().map(ProjectLiceseListDTO::new).distinct().collect(Collectors.toList());
			projectDto.setLicenseCnt(licenseList.size());
			//유저
			projectDto.setUserId(project.getUser().getUserId());
			projectDto.setUsername(project.getUser().getName());
			projectListDto.add(projectDto);
		
		}
		
		analyProjectByDepartDto.setProjectList(projectListDto);
		analyProjectByDepartDto.setTotalPages(projectList.getTotalPages());
		
		return analyProjectByDepartDto;
	}
	
	// 분석된 프로젝트의 매핑된 오픈소스 목록
	public AnalyMappedOpensouceListDTO getMappedOpensourceListByProject (String gitProjectId, Long gitType, int size, int currentPage) {
		Project project = projectRepository.findByGitProjectIdAndGitType(gitProjectId, gitType);
		Long projectId = project.getProjectId();
		PageRequest pageRequest = PageRequest.of(currentPage - 1, size);
		Page<OpensourceProject> opensourceList = opensourceProjectRepository.findByProject(project, pageRequest);
		List<OpensourceDTO> mappedopensourceListDto = new ArrayList<OpensourceDTO>();
		mappedopensourceListDto = opensourceList.getContent().stream().map(OpensourceDTO::new).collect(Collectors.toList());	
		AnalyMappedOpensouceListDTO mappedList = new AnalyMappedOpensouceListDTO();
		mappedList.setTotalPages(opensourceList.getTotalPages());
		mappedList.setMappedList(mappedopensourceListDto);
		return mappedList;
	}
	
	// 분석된 프로젝트의 언매핑된 오픈소스 목록
	public AnalyUnmappedOpensouceListDTO getUnmappedOpensourceListByProject(String gitProjectId, Long gitType, int size, int currentPage) {
		Project project = projectRepository.findByGitProjectIdAndGitType(gitProjectId, gitType);
		Long projectId = project.getProjectId();
		PageRequest pageRequest = PageRequest.of(currentPage - 1, size);
		Page<UnmappedOpensource> unMappedOpensouce = unmappedOpensourceRepository.findByProject(project, pageRequest);
		List <UnmappendOpensourceDTO> unmappedListDto = new ArrayList<UnmappendOpensourceDTO>();
		unmappedListDto = unMappedOpensouce.getContent().stream().map(UnmappendOpensourceDTO::new).collect(Collectors.toList());
		AnalyUnmappedOpensouceListDTO analyUnmappedList = new AnalyUnmappedOpensouceListDTO();
		analyUnmappedList.setTotalPages(unMappedOpensouce.getTotalPages());
		analyUnmappedList.setUnmappedList(unmappedListDto);
		return analyUnmappedList;
	}
	

	// 분석된 프로젝트의 라이선스 목록
	public AnalyLicenseListDTO getLicenseListByProject(String gitProjectId, Long gitType, int size, int currentPage) {
		Project project = projectRepository.findByGitProjectIdAndGitType(gitProjectId, gitType);
		Long projectId = project.getProjectId(); 
		PageRequest pageRequest = PageRequest.of(currentPage - 1, size);
		Page<License> projectLicense = licenseRepository.findAllByProjectId(projectId, pageRequest);
		List<ProjectLiceseListDTO> licenseList = new ArrayList<ProjectLiceseListDTO>();
		licenseList = projectLicense.getContent().stream().map(ProjectLiceseListDTO::new).collect(Collectors.toList());
		AnalyLicenseListDTO analyLicenseList = new AnalyLicenseListDTO();
		analyLicenseList.setTotalPages(projectLicense.getTotalPages());
		analyLicenseList.setLicenseList(licenseList);
		return analyLicenseList;
	}
}
