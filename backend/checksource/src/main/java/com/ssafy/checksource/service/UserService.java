package com.ssafy.checksource.service;

import com.ssafy.checksource.config.security.JwtTokenProvider;
import com.ssafy.checksource.model.dto.TokenResultDTO;
import com.ssafy.checksource.model.dto.UserDTO;
import com.ssafy.checksource.model.entity.Depart;
import com.ssafy.checksource.model.entity.Job;
import com.ssafy.checksource.model.repository.DepartRepository;
import com.ssafy.checksource.model.repository.JobRepository;
import com.ssafy.checksource.model.repository.UserRepository;
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
	
	public boolean loginApi(SsoDTO ssoDto) {
		String userId = ssoDto.getUserId();
		String password = ssoDto.getPassword();
		Login loginUser = ssoRepository.findByUserIdAndPassword(userId, password);
		System.out.println(loginUser);
		if(loginUser != null)
			return true;
		return false;
	}

	public TokenResultDTO checkUser(String userId) {
		User user = userRepository.findByUserId(userId);
		TokenResultDTO tokenResultDTO = new TokenResultDTO();

		if (user != null) {
			if (user.getFlag()) {
				tokenResultDTO.setToken(user.getToken());
				tokenResultDTO.setName(user.getUsername());
				tokenResultDTO.setCode(0);

				return tokenResultDTO;
			} else {
				tokenResultDTO.setCode(1);

				return tokenResultDTO;
			}
		} else {
			User newUser = new User();
			newUser.setUserId(userId);

			userRepository.save(newUser);

			tokenResultDTO.setCode(1);

			return tokenResultDTO;
		}
	}

	public TokenResultDTO userForm(UserDTO userDTO) {
		User user = userRepository.findByUserId(userDTO.getUserId());
		TokenResultDTO tokenResultDTO = new TokenResultDTO();

		if(user!= null){
			String token = jwtTokenProvider.generateToken(user.getUserId());
			Depart depart = departRepository.findByDepartId(userDTO.getDepartId());
			Job job = jobRepository.findByJobId(userDTO.getJobId());

			user.setUserName(userDTO.getName());
			user.setToken(token);
			user.setGitlapId(userDTO.getGitLabId());
			user.setDepart(depart);
			user.setJob(job);
			user.setFlag(true);

			userRepository.save(user);

			tokenResultDTO.setCode(0);
			tokenResultDTO.setName(user.getUsername());
			tokenResultDTO.setToken(token);
		} else {
			tokenResultDTO.setCode(2);
		}


		return tokenResultDTO;
	}

}
