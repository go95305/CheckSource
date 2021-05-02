package com.ssafy.checksource.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.checksource.model.dto.DependencyDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AnalyzeService {
	
	//pom.xml 파서
	//groupId, artifactId, version만 파싱하여 return
	public DependencyDTO pomxmlParsing(String xml) {
		
		
		
		return null;
	}

	
}
