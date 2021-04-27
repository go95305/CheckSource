package com.ssafy.checksource.controller;

import com.ssafy.checksource.model.dto.UserDTO;
import com.ssafy.checksource.model.dto.UserInfoDTO;
import com.ssafy.checksource.model.dto.UserInputDTO;
import com.ssafy.checksource.model.dto.UserUpdateDTO;

import org.springframework.web.bind.annotation.*;

import com.ssafy.checksource.model.dto.SsoDTO;
import com.ssafy.checksource.service.UserService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	
	@ApiOperation(value = "테스트")
	@PostMapping("/test")
	public void test() {
		
	}
	
	
	@ApiOperation(value = "로그인 API 요청", notes = "- 로그인 API 요청 \n -ssoDto에 userId와 password입력  \n -아이디, 비밀번호 둘다 string타입 \n -로그인 성공시 true, false반환 -> true일 경우 checkUser API 요청 / false일 경우 아이디 비밀번호 확인 메세지 출력 \n [테스트계정] 아이디: 111111 / 비밀번호: 1234")
	@PostMapping("/loginApi")
	public boolean test(@RequestBody SsoDTO ssoDto) {
		boolean check = userService.loginApi(ssoDto);
		return check;
	}

	@ApiOperation(value = "회원정보 입력여부 체크", notes = "- loginApi에서 true를 받으면 회원정보 입력여부 체크를 받는다. 회원정보가 입력되어 있어야지 서비스 이용가능 \n - inputForm에 입력한 아이디를 RequestParam에 보낸다. \n - UserDTO의 flag = false일때 회원정보 입력 안한 상태 -> 회원정보 입력 화면  \n - UserDTO의 flag = true일때 회원정보 입력 o 로그인 성공-> 회원정보 반환(이름,토큰,부서번호,직급번호)  \n - userId에 이상한 값 입력시 flag = false 반환")
	@GetMapping("/checkUser")
	public UserDTO checkUser(@RequestParam String userId) {
		UserDTO userDto = userService.checkUser(userId);
		return userDto;
	}

	@ApiOperation(value = "회원정보 입력", notes = "- 최초로그인 후 회원정보를 입력한다. 입력해야 서비스 이용가능함 \n - 입력정보는 UserInputDTO에 담아 보낸다. \n - gitlab아이디 제외 나머지는 필수로 입력받아야한다. * ")
	@PostMapping("/userForm")
	public UserDTO saveUserForm(@RequestBody UserInputDTO userInputDto) {
		UserDTO userDto = userService.saveUserForm(userInputDto);
		return userDto;
	}
	
	@ApiOperation(value = "로그아웃", notes = "- 로그아웃시 header에 token 담아보내기  \n - 로그아웃시 front에서 localstorage에 담은 user 정보 날리기")
	@GetMapping("/logout")
	public void logout(@RequestHeader("TOKEN") String token) {
		userService.logout(token);
	}
	
	@ApiOperation(value = "회원정보 수정", notes = "- 회원정보를 수정한다.  header에 token과  UserUpdateDTO에 수정할 정보를 담아보낸다. \n - UserUpdateDTO에 gitlap아이디를 제외하고 모두 필수입력값이다. \n - 회원정보 수정완료시 수정완료한 회원정보를 UserDTO에 담아 반환한다. \n - 반환된 정보를 localstorage에 새로 업데이트한다.")
	@PutMapping("/update")
	public UserDTO updateUserForm(@RequestHeader("TOKEN") String token, @RequestBody UserUpdateDTO userUpdateDto) {
		return userService.updateUserForm(token, userUpdateDto);
	}
	

	@ApiOperation(value = "회원정보 보기", notes = "- 회원정보를 보여준다.  header에 token을 담아보낸다. \n - UserInfoDTO에 회원테이블의 모든 정보를 담아 보여준다.")
	@GetMapping("/info")
	public UserInfoDTO getUserInfo (@RequestHeader("TOKEN") String token) {
		return userService.getUserInfo(token);
	}
}
