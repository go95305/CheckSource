package com.ssafy.checksource.service;

import com.ssafy.checksource.config.security.JwtTokenProvider;
import com.ssafy.checksource.model.dto.UserDTO;
import com.ssafy.checksource.model.dto.UserInfoDTO;
import com.ssafy.checksource.model.dto.UserInputDTO;
import com.ssafy.checksource.model.dto.UserUpdateDTO;
import com.ssafy.checksource.model.entity.Depart;
import com.ssafy.checksource.model.entity.Job;
import com.ssafy.checksource.model.repository.DepartRepository;
import com.ssafy.checksource.model.repository.JobRepository;
import com.ssafy.checksource.model.repository.UserRepository;

import org.modelmapper.ModelMapper;
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
	private final UserRepository userRepository;
	private final JwtTokenProvider jwtTokenProvider;
	private final DepartRepository departRepository;
	private final JobRepository jobRepository;
	private final ModelMapper modelMapper = new ModelMapper();

	//로그인 API요청
	public boolean loginApi(SsoDTO ssoDto) {
		String userId = ssoDto.getUserId();
		String password = ssoDto.getPassword();
		Login loginUser = ssoRepository.findByUserIdAndPassword(userId, password);
		if (loginUser != null)
			return true;
		return false;
	}

	//회원정보 입력여부 체크
	public UserDTO checkUser(String userId) {
		User user = userRepository.findByUserId(userId);
		UserDTO userDto = new UserDTO();

		if (user != null) {// 최초로그인 x
			if (user.getFlag()) {// 회원 정보 o -> 로그인 성공
				// 토큰 새로 발급
				String token = jwtTokenProvider.generateToken(user.getUserId());
				user.setToken(token);
				userRepository.save(user);
				userDto = modelMapper.map(user, UserDTO.class);
				return userDto;
			}
			//최초로그인은 아니지만 회원정보 미입력시 flag = false
		}
		else {// 최초 로그인 o -> user테이블에 저장
			if(ssoRepository.findById(userId).isPresent()) {
				User newUser = new User();
				newUser.setUserId(userId);
				userRepository.save(newUser);
			}
		}
		return userDto;
	}

	//회원정보 입력 -> 로그인 성공시 회원정보 반환
	public UserDTO saveUserForm(UserInputDTO userInputDto) {
		User user = userRepository.findById(userInputDto.getUserId()).orElseThrow(() -> new IllegalArgumentException("no user data"));
		UserDTO userDto = new UserDTO();

		String token = jwtTokenProvider.generateToken(user.getUserId());
		user = modelMapper.map(userInputDto, User.class);
		user.setToken(token);
		Depart depart = departRepository.findById(userInputDto.getDepart()).orElseThrow(() -> new IllegalArgumentException("no depart data"));
		Job job = jobRepository.findById(userInputDto.getJob()).orElseThrow(() -> new IllegalArgumentException("no job data"));
		user.setDepart(depart);
		user.setJob(job);
		user.setFlag(true); //회원정보 입력시 flag = true
		userRepository.save(user);
		
		userDto = modelMapper.map(user, UserDTO.class);
		return userDto;
	}
	
	//로그아웃
	public void logout(String token) {
		String userId = jwtTokenProvider.getUserId(token);
		User user = userRepository.findByUserId(userId);
		user.setToken(null);
		userRepository.save(user);	
	}

	//회원정보 수정
	public UserDTO updateUserForm(String token, UserUpdateDTO userUpdateDto) {
		String userId = jwtTokenProvider.getUserId(token);
		User user = userRepository.findByUserId(userId);
		UserDTO userDto = new UserDTO();
		
		Depart depart = departRepository.findById(userUpdateDto.getDepart()).orElseThrow(() -> new IllegalArgumentException("no depart data"));
		Job job = jobRepository.findById(userUpdateDto.getJob()).orElseThrow(() -> new IllegalArgumentException("no job data"));
		user.setDepart(depart);
		user.setJob(job);
		user.setGitlapId(userUpdateDto.getGitlapId());
		user.setName(userUpdateDto.getName());
		userRepository.save(user);
		
		userDto = modelMapper.map(user, UserDTO.class);
			
		return userDto;
	}
	
	//회원 정보 보여주기
	public UserInfoDTO getUserInfo (String token) {
		String userId = jwtTokenProvider.getUserId(token);
		User user = userRepository.findByUserId(userId);
		UserInfoDTO userInfoDto = new UserInfoDTO();
		userInfoDto = modelMapper.map(user, UserInfoDTO.class);
		return userInfoDto;
	}
}
