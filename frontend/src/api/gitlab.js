/*
  gitlab 계정 연동, 프로젝트 목록 조회를 위한 JS 파일
  
  @version 21-05-08
  @author 남우진
*/

import http from "./http";

const baseUrl = [
    "http://gitlab.checksource.io:8081",
    "http://gitlab.checksource.io:8082",
];

function getBaseUrlList() {
    return baseUrl;
}

function getBaseUrl(index) {
    return baseUrl[index];
}

//gitlab 계정연동
function createGitLabConnect(account) {
    return http.get(`/git/gitlabConnect`, {
        params: {
            gitlabId: account.gitlabId,
            username: account.username,
        },
    });
}

//gitlab 계정연동 수정
function deleteGitLabConnect(gitlabId) {
    return http.delete(`/git/gitlabConnect`, {
        params: { gitlabId: gitlabId },
    });
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
    getBaseUrlList,
    getBaseUrl,
    createGitLabConnect,
    deleteGitLabConnect,
    readGitLabProjects,
    verifyGitLabProjects,
};
