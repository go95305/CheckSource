import http from "./http.js";

//전체 통계 정보 불러오기
function readStatistics() {
	return http.get(`/dashboard/statistics`);
}

//전체 통계 정보 불러오기
function readTopFive() {
	return http.get(`/dashboard/top5`);
}

//부서별 통계 정보 불러오기 = 그래프
function readTopFiveDepart(departId) {
	return http.get(`/dashboard/top5/${departId}`);
}

//부서별 통계 정보 = status
function readDepartmentStatus(departId) {
	return http.get(`/dashboard/statistics/${departId}`);
}

export default {
	readStatistics,
	readTopFive,
	readTopFiveDepart,
	readDepartmentStatus,
};
