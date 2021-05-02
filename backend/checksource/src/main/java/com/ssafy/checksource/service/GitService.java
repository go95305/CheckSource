package com.ssafy.checksource.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.ssafy.checksource.config.security.JwtTokenProvider;
import com.ssafy.checksource.model.repository.DepartRepository;
import com.ssafy.checksource.model.repository.JobRepository;
import com.ssafy.checksource.model.repository.SsoRepository;
import com.ssafy.checksource.model.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional

public class GitService {

	@Autowired
	RestTemplate restTemplate;
	private final String baseUrl = "https://gitlab.com/api/v4/";
	public void gitConnect() {
		String url = baseUrl + "users/ekgml3765/projects";
		
		String s = null;
		try {
			s = restTemplate.getForObject(url, String.class);
			System.out.println(s);
		} catch (Exception e) { //사용자 없을때
			
			System.out.println(e.getMessage());
			
		}
		
	}
}
