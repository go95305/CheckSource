import http from "./http";

//분석된 프로젝트 라이선스 목록 불러오기
function readVerifiedLicenseList(projectId) {
	return http.get(`/analyze/licenseList`, {
		params: {
			projectId: projectId,
		},
	});
}

export default { readVerifiedLicenseList };
