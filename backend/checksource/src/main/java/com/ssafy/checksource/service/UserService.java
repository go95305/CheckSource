package com.ssafy.checksource.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.checksource.model.dto.SsoDTO;
import com.ssafy.checksource.model.entity.Login;
import com.ssafy.checksource.model.entity.User;
import com.ssafy.checksource.model.repository.SsoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

	private final SsoRepository ssoRepository;
	
	public boolean loginApi(SsoDTO ssoDto) {
		Long userId = ssoDto.getUserId();
		String password = ssoDto.getPassword();
		Login loginUser = ssoRepository.findByUserIdAndPassword(userId, password);
		System.out.println(loginUser);
		return false;
	}
	
}
