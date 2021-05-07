import http from "./http";

//로그인
function login(loginInfo) {
	return http.post(`user/loginApi`, loginInfo);
}

//회원정보 조회
function checkUser(userId) {
	return http.get(`user/checkUser`, {
		params: {
			userId: userId,
		},
	});
}

//회원정보 입력
function userForm(userInfo) {
	return http.post(`user/userForm`, userInfo);
}

function userUpdate(userInfo) {
	return http.put(`user/update`, userInfo);
}

export default { login, checkUser, userForm, userUpdate };
