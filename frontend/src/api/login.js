import http from "./http";

//log in api
export function login(loginInfo) {
  return http.get(`user/login/${loginInfo}`);
}
