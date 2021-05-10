/*
  gitlab 계정 연동, 프로젝트 목록 조회를 위한 JS 파일
  
  @version 21-05-08
  @author 남우진
*/

import http from "./http";

//gitlab 계정연동
function createGitLabConnect(account) {
	return http.get(`/git/gitlabConnect`, {
		params: {
			accessToken: account.accessToken,
			username: account.username,
		},
	});
}

//gitlab 계정연동 수정
function updateGitLabConnect(account) {
	return http.put(
		`/git/gitlabConnect?accessToken=${account.accessToken}&username=${account.username}&gitlabId=${account.gitlabId}`
	);
}

//gitlab 계정연동 수정
function deleteGitLabConnect(gitlabId) {
	return http.delete(`/git/gitlabConnect`, { params: { gitlabId: gitlabId } });
}

function readGitLabProjects(gitlabId) {
	return http.get("/git/projects", {
		params: {
			gitlabId: gitlabId,
		},
	});
}

function verifyGitLabProjects(gitlabId, projectList) {
	return http.post(`/git/projects?gitlabId=${gitlabId}`, projectList);
}

export default {
	createGitLabConnect,
	updateGitLabConnect,
	deleteGitLabConnect,
	readGitLabProjects,
	verifyGitLabProjects,
};
