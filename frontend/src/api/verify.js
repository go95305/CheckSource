import http from "./http";

//분석된 프로젝트 목록 불러오기
function readVerifiedProjectList(departId) {
    return http.get(`/analyze/projectList`, {
        params: {
            departId: departId,
        },
    });
}

//분석된 프로젝트 오픈소스 목록 불러오기
function readVerifiedProjectName(projectId) {
    return http.get(`/analyze/projectName`, {
        params: {
            projectId: projectId,
        },
    });
}

//분석된 프로젝트 오픈소스 목록 불러오기
function readVerifiedOpenSourceList(projectId) {
    return http.get(`/analyze/opensourceList`, {
        params: {
            projectId: projectId,
        },
    });
}

//분석된 프로젝트 라이선스 목록 불러오기
function readVerifiedLicenseList(projectId) {
    return http.get(`/analyze/licenseList`, {
        params: {
            projectId: projectId,
        },
    });
}

export default {
    readVerifiedProjectList,
    readVerifiedProjectName,
    readVerifiedOpenSourceList,
    readVerifiedLicenseList,
};
