import http from "./http";

//log in api
function login(loginInfo) {
  return http.post(`user/loginApi`, {
    password: loginInfo.pw,
    userId: loginInfo.userId,
  });
}

function checkUser(userId) {
  return http.get(`user/checkUser`, {
    params: {
      userId: userId,
    },
  });
}

export default { login, checkUser };
