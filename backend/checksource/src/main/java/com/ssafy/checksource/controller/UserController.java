package com.ssafy.checksource.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.checksource.model.dto.SsoDTO;
import com.ssafy.checksource.service.UserService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	
	//테스트
	@PostMapping("/test")
	public void test(@RequestBody SsoDTO ssoDto) {
		userService.loginApi(ssoDto);
	}
	
	//SSO 테이블 API 요청
	
	//로그인
	//로그아웃
}
