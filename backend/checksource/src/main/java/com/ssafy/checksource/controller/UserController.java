package com.ssafy.checksource.controller;

import com.ssafy.checksource.model.dto.TokenResultDTO;
import com.ssafy.checksource.model.dto.UserDTO;
import org.springframework.web.bind.annotation.*;

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
	public boolean test(@RequestBody SsoDTO ssoDto) {
		boolean check = userService.loginApi(ssoDto);

		return check;
	}

	@GetMapping("/checkUser")
	public TokenResultDTO checkUser(@RequestParam String userId) {
		TokenResultDTO tokenResultDTO = userService.checkUser(userId);

		return tokenResultDTO;
	}

	@PostMapping("/userForm")
	public TokenResultDTO userForm(@RequestBody UserDTO userDTO) {
		TokenResultDTO tokenResultDTO = userService.userForm(userDTO);

		return tokenResultDTO;
	}
	
	//로그인
	//로그아웃 할때 user테이블 token을 null값으로
}
