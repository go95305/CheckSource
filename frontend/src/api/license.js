import http from "./http.js";

//라이선스 목록 불러오기
function readLicenseList(keyword, page, pageSize, typeFilter) {
	return http.get(`/license/getList`, {
		params: {
			keyword: keyword,
			page: page,
			pageSize: pageSize,
			typeFilter: typeFilter,
		},
	});
}

//라이선스 상세 정보 불러오기
function getDetailLicense(licenseId) {
	return http.get(`/license/getDetail/${licenseId}`, {
		params: {
			licenseId: licenseId,
		},
	});
}

//라이선스 추가
function addLicense(licenseInfo) {
	return http.post(`/license/addLicense`, licenseInfo);
}

//오픈소스 업데이트
function updateLicense(licenseInfo) {
	return http.put(`/license/updateLicense`, licenseInfo);
}

//오픈소스 삭제하기
function deleteLicense(licenseId) {
	return http.put(`/license/deleteLicense`, {
		licenseId: licenseId,
	});
}

export default {
	readLicenseList,
	getDetailLicense,
	addLicense,
	updateLicense,
	deleteLicense,
};
