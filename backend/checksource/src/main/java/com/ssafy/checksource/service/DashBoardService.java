package com.ssafy.checksource.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.ssafy.checksource.config.security.JwtTokenProvider;
import com.ssafy.checksource.model.dto.StatisticsByDepartDTO;
import com.ssafy.checksource.model.dto.StatisticsTotalDTO;
import com.ssafy.checksource.model.dto.Top5OpensourceDTO;
import com.ssafy.checksource.model.entity.Depart;
import com.ssafy.checksource.model.entity.License;
import com.ssafy.checksource.model.entity.Opensource;
import com.ssafy.checksource.model.repository.DepartRepository;
import com.ssafy.checksource.model.repository.LicenseRepository;
import com.ssafy.checksource.model.repository.OpensourceProjectRepository;
import com.ssafy.checksource.model.repository.OpensourceRepository;
import com.ssafy.checksource.model.repository.ProjectRepository;
import com.ssafy.checksource.model.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class DashBoardService {

	
	private final JwtTokenProvider jwtTokenProvider;
	private final ModelMapper modelMapper = new ModelMapper();
	private final UserRepository userRepository;
	private final ProjectRepository projectRepository;
	private final DepartRepository departRepository;
	private final OpensourceRepository opensourceRepository;
	private final LicenseRepository licenseRepository;
	private final OpensourceProjectRepository opensourceProjectRepository;
	
	
	//전체 top 오픈소스 5
	public void getTotalTop5Opensource() {
		
	}
	
	// 부서벌 top 오픈소스 5
	public List<Top5OpensourceDTO> getTop5OpensourceByDepart(Long departId) {
		List <Top5OpensourceDTO> top5ListDto = new ArrayList<Top5OpensourceDTO>();
		//쿼리
		List<Object> top5List = opensourceRepository.findByTop5(departId);
		for (Object object : top5List) {
			Gson gson = new Gson();
			
		}
		
		//set
		return top5ListDto;
	}
	
	//전제 라이선스 의무 warning
	public void getTotalLicenseWarning() {
		
	}
	
	
	// 부서별 라이선스 의무 warning
	public void getlicenseWarningByDepart (Long dapartId, int currentPage, int size) {
		//페이징 필요, 각각의 프로젝트마다 의무 워닝수 필요
		PageRequest pageRequest = PageRequest.of(currentPage - 1, size);
		//프로젝트 당 라이선스 의무워닝이 있는 것만 세야해.
	
	}
	
	// 전체 통계 수치
	public StatisticsTotalDTO getTotalStatistics () {
		StatisticsTotalDTO statisticsTotalDto = new StatisticsTotalDTO();
		//전체
		int totalProjectCnt = 0;
		int totalOpensourceCnt = 0;
		int totalLicenseCnt = 0;
		int totalWarningCnt = 0;
		//각각
		List<StatisticsByDepartDTO> statisticsList = new ArrayList<StatisticsByDepartDTO>();
		//7개 부서
		for (long i = 1; i <= 7; i++) {
			StatisticsByDepartDTO statisticsByDepartDto = new StatisticsByDepartDTO();
			statisticsByDepartDto = getStatisticsByDepart(i);
			totalProjectCnt += statisticsByDepartDto.getProjectCnt();
			totalOpensourceCnt += statisticsByDepartDto.getOpensourceCnt();
			totalLicenseCnt += statisticsByDepartDto.getLicenseCnt();
			totalWarningCnt += statisticsByDepartDto.getWarningCnt();
			statisticsList.add(statisticsByDepartDto);
		}
		
		//set
		statisticsTotalDto.setStatisticsList(statisticsList);
		statisticsTotalDto.setTotalLicenseCnt(totalLicenseCnt);
		statisticsTotalDto.setTotalOpensourceCnt(totalOpensourceCnt);
		statisticsTotalDto.setTotalProjectCnt(totalProjectCnt);
		statisticsTotalDto.setTotalWarningCnt(totalWarningCnt);
		return statisticsTotalDto;
	}
	
	
	// 부서별 통계 수치
	public StatisticsByDepartDTO getStatisticsByDepart (Long departId) {
		
		//부서
		StatisticsByDepartDTO statisticsByDepartDto = new StatisticsByDepartDTO();
		Depart depart = departRepository.findById(departId).orElseThrow(() -> new IllegalArgumentException("no depart data"));
		int projectCnt = projectRepository.findAllByDepart(depart).size(); //총 프로젝트 수
		List<Opensource> opensouceList = opensourceRepository.findByDepart(departId);
		int opensourceCnt = opensouceList.size(); //총 오픈소스 종류 수
		List<License> licenseList = licenseRepository.findByDepart(departId);
		int licenseCnt = licenseList.size(); //총 라이선스 종류 수
		int warningCnt = 0;  //총 워닝 종류 수
		for (License license : licenseList) {
			if(license.getSourceopen().length() > 0) //소스코드 공개여부 null이 아니면 risk한 내용
				warningCnt++;
		}
		
		//set
		statisticsByDepartDto.setDepartId(departId);
		statisticsByDepartDto.setProjectCnt(projectCnt);
		statisticsByDepartDto.setOpensourceCnt(opensourceCnt);
		statisticsByDepartDto.setLicenseCnt(licenseCnt);
		statisticsByDepartDto.setWarningCnt(warningCnt);
		return statisticsByDepartDto;
	}
	
	
	
}
