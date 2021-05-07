import http from "./http";

//로그인
function getGitConnect() {
	return http.get(`user/loginApi`, loginInfo);
}

export default { getGitConnect };
