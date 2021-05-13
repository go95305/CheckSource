package com.ssafy.checksource.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.checksource.config.security.JwtTokenProvider;
import com.ssafy.checksource.model.dto.AnalyOpensourceListDTO;
import com.ssafy.checksource.model.dto.OpensourceDTO;
import com.ssafy.checksource.model.dto.ProjectLiceseListDTO;
import com.ssafy.checksource.model.dto.ProjectListByDepartDTO;
import com.ssafy.checksource.model.dto.UnmappendOpensourceDTO;
import com.ssafy.checksource.model.entity.Depart;
import com.ssafy.checksource.model.entity.License;
import com.ssafy.checksource.model.entity.LicenseOpensource;
import com.ssafy.checksource.model.entity.Opensource;
import com.ssafy.checksource.model.entity.OpensourceProject;
import com.ssafy.checksource.model.entity.Project;
import com.ssafy.checksource.model.entity.UnmappedOpensource;
import com.ssafy.checksource.model.repository.DepartRepository;
import com.ssafy.checksource.model.repository.LicenseOpensourceRepository;
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
	private final OpensourceProjectRepository opensourceProjectRepository;
	private final UnmappedOpensourceRepository unmappedOpensourceRepository;
	
	//summary
	
	// 부서별 분석된 프로젝트 목록
	public List<ProjectListByDepartDTO> getProjectListByDepart(Long departId) {
		List<ProjectListByDepartDTO> projectListDto = new ArrayList<ProjectListByDepartDTO>();
		Depart dapart = departRepository.findById(departId).orElseThrow(() -> new IllegalArgumentException("no depart data"));
		List<Project> projectList = projectRepository.findByDepart(dapart);
		
		for (Project project : projectList) {
			ProjectListByDepartDTO projectDto = new ProjectListByDepartDTO();
			projectDto = modelMapper.map(project, ProjectListByDepartDTO.class);
			//프로젝트 id별 매핑된 오픈소스 갯수 
			List<OpensourceProject> opensourceList = new ArrayList<OpensourceProject>();
			opensourceList = opensourceProjectRepository.findByProject(project);
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
		return projectListDto;
	}

	// 분석된 프로젝트의 오픈소스 목록
	public AnalyOpensourceListDTO getOpensourceListByProject(String projectId) {
		Project project = projectRepository.findByProjectId(projectId);
		AnalyOpensourceListDTO analyOpensourceListDto = new AnalyOpensourceListDTO();	
		
		//매핑
		List<LicenseOpensource> licenseOpensourceList = new ArrayList<LicenseOpensource>();
		licenseOpensourceList = licenseOpensourceRepository.findAllByProjectId(projectId);
		List<OpensourceDTO> mappedopensourceList = new ArrayList<OpensourceDTO>();
		mappedopensourceList = licenseOpensourceList.stream().map(OpensourceDTO::new).distinct().collect(Collectors.toList());
		
		for (int i = 0; i < mappedopensourceList.size(); i++) {
			for (LicenseOpensource licenseOpensource : licenseOpensourceList) {
				OpensourceDTO opensourceDto = mappedopensourceList.get(i);
				if(opensourceDto.getOpensourceId() == licenseOpensource.getOpslic_id().getOpensourceId()) {
					opensourceDto.getLicenseNameList().add(licenseOpensource.getLicense().getName());
					mappedopensourceList.set(i, opensourceDto);
				}
			}
		}
		analyOpensourceListDto.setMappedList(mappedopensourceList);
		
		//언매핑
		List <UnmappendOpensourceDTO> unmappedListDto = new ArrayList<UnmappendOpensourceDTO>();
		unmappedListDto = unmappedOpensourceRepository.findByProject(project).stream().map(UnmappendOpensourceDTO::new).collect(Collectors.toList());
		analyOpensourceListDto.setUnmappedList(unmappedListDto);
		return analyOpensourceListDto;
	}

	// 분석된 프로젝트의 라이선스 목록 -> 페이징 필요
	public List<ProjectLiceseListDTO> getLicenseListByProject(String projectId) {
		List<LicenseOpensource> licenseOpensourceList = new ArrayList<LicenseOpensource>();
		licenseOpensourceList = licenseOpensourceRepository.findAllByProjectId(projectId);		
		List<ProjectLiceseListDTO> licenseList = licenseOpensourceList.stream().map(ProjectLiceseListDTO::new).distinct().collect(Collectors.toList());
		return licenseList;
	}
}
