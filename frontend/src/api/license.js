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

function getDetailLicense(licenseId) {
	return http.get(`/license/getDetail/${licenseId}`, {
		params: {
			licenseId: licenseId,
		},
	});
}

function addLicense(licenseInfo) {
	return http.post(`/license/addLicense`,licenseInfo);
  }

export default { readLicenseList, getDetailLicense, addLicense };
