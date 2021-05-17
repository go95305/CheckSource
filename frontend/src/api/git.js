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

//github 계정연동
function createGitHubConnect(account) {
  return http.get(`/git/githubConnect`, {
    params: {
      personalAccessToken: account.personalAccessToken,
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
//github 계정연동 수정
function deleteGitHubConnect(githubId) {
  return http.delete(`/git/githubConnect`, {
    params: { githubId: githubId },
  });
}

function readGitLabProjects(gitlabId) {
  return http.get("/git/projects", {
    params: {
      gitlabId: gitlabId,
    },
  });
}

function readGitHubProjects(githubId) {
  return http.get("/git/github/projects", {
    params: {
      githubId: githubId,
    },
  });
}

function readProjectBranches(gitlabId, projectId) {
  return http.get("/git/branches", {
    params: {
      gitlabId: gitlabId,
      projectId: projectId,
    },
  });
}
function readHubProjectBranches(githubId, name, username) {
  return http.get("/git/github/branches", {
    params: {
      githubId: githubId,
      repo: name,
      username: username,
    },
  });
}

function verifyGitLabProjects(gitlabId, projectList) {
  return http.post(`/git/projects?gitlabId=${gitlabId}`, projectList);
}
function verifyGitHubProjects(githubId, projectList) {
  console.log(githubId)
  console.log(projectList)
  return http.post(`/git/github/projects?githubId=${githubId}`, projectList);
}



// 프로젝트 삭제
function deleteProject(projectId, gitlabId) {
  return http.delete(`/git/projects`, {
    params: {
      projectId: projectId,
      gitlabId: gitlabId,
    },
  });
}

export default {
  getBaseUrlList,
  getBaseUrl,
  createGitLabConnect,
  deleteGitLabConnect,
  readGitLabProjects,
  readProjectBranches,
  verifyGitLabProjects,
  deleteProject,
  createGitHubConnect,
  deleteGitHubConnect,
  readGitHubProjects,
  verifyGitHubProjects,
  readHubProjectBranches,
};
