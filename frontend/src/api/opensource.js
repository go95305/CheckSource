import http from "./http";

//오픈소스 목록 불러오기
function readOpenSourceList(keyword, page, pageSize, typeFilter) {
	return http.get(`/opensource/getList`, {
		params: {
			keyword: keyword,
			page: page,
			pageSize: pageSize,
			typeFilter: typeFilter,
		},
	});
}

//검색한 라이선스 이름 목록 받기
function getLicenseName(licenseName) {
	return http.get(`/license/getNameList`, {
		params: {
			keyword: licenseName,
		},
	});
}

//오픈소스 추가
function addOpenSource(opensourceInfo) {
	return http.post(`/opensource/addOpensource`, opensourceInfo);
}

//오픈소스 업데이트
function updateOpenSource(opensourceInfo) {
	return http.put(`/opensource/updateOpensource`, opensourceInfo);
}

//오픈소스 삭제하기
function deleteOpenSource(opensourceId) {
	return http.delete(`/opensource/deleteOpensource`, {
		params: {
			opensourceId: opensourceId,
		},
	});
}

//오픈소스 상세정보 받기
function getDetailOpensource(opensourceId) {
	return http.get(`/opensource/getDetail/${opensourceId}`, {
		params: {
			opensourceId: opensourceId,
		},
	});
}
export default {
	readOpenSourceList,
	getLicenseName,
	addOpenSource,
	updateOpenSource,
	deleteOpenSource,
	getDetailOpensource,
};
