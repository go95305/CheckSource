import http from "./http";

//분석된 프로젝트 목록 불러오기
function readVerifiedProjectList(currentPage, departId, keyword, size, time) {
	return http.get(`/analyze/projectList`, {
		params: {
			currentPage: currentPage,
			departId: departId,
			keyword: keyword,
			size: size,
			time: time,
		},
	});
}

//분석된 프로젝트 오픈소스 정보 불러오기
function readVerifiedProjectInfo(gitType, projectId) {
	return http.get(`/analyze/projectInfo`, {
		params: {
			gitType: gitType,
			projectId: projectId,
		},
	});
}

//분석된 프로젝트 오픈소스 목록 불러오기
function readVerifiedMappedOpenSourceList(
	currentPage,
	gitType,
	projectId,
	size
) {
	return http.get(`/analyze/mappedOpensourceList`, {
		params: {
			currentPage: currentPage,
			gitType: gitType,
			projectId: projectId,
			size: size,
		},
	});
}

//분석된 프로젝트 오픈소스 목록 불러오기
function readVerifiedUnmappedOpenSourceList(
	currentPage,
	gitType,
	projectId,
	size
) {
	return http.get(`/analyze/unMappedOpensourceList`, {
		params: {
			currentPage: currentPage,
			gitType: gitType,
			projectId: projectId,
			size: size,
		},
	});
}

//분석된 프로젝트 라이선스 목록 불러오기
function readVerifiedLicenseList(gitType, currentPage, size, projectId) {
	return http.get(`/analyze/licenseList`, {
		params: {
			gitType: gitType,
			currentPage: currentPage,
			size: size,
			projectId: projectId,
		},
	});
}

// 분석된 프로젝트 요약정보
function readVerifiedSummary(gitType, projectId) {
	return http.get(`/analyze/summary`, {
		params: {
			gitType: gitType,
			projectId: projectId,
		},
	});
}

export default {
	readVerifiedProjectList,
	readVerifiedProjectInfo,
	readVerifiedMappedOpenSourceList,
	readVerifiedUnmappedOpenSourceList,
	readVerifiedLicenseList,
	readVerifiedSummary,
};
