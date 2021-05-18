import http from "./http.js";

//전체 통계 정보 불러오기
function readStatistics() {
	return http.get(`/dashboard/statistics`);
}

//전체 통계 정보 불러오기
function readTopFive() {
	return http.get(`/dashboard/top5`);
}

//전체 부서별 프로젝트 warning
function readDashboardWarning(currentPage, size, time) {
	return http.get(`/dashboard/warning`, {
		params: {
			currentPage: currentPage,
			size: size,
			time: time,
		},
	});
}

//부서별 통계 정보 불러오기 = 그래프
function readTopFiveDepart(departId) {
	return http.get(`/dashboard/top5/${departId}`);
}

//부서별 통계 정보 = status
function readDepartmentStatus(departId) {
	return http.get(`/dashboard/statistics/${departId}`);
}

//부서별 통계 정보 = 부서별 프로젝트 warning
function readDepartmentDashboardWarning(currentPage, departId, size, time) {
	return http.get(`/dashboard/warning/${departId}`, {
		params: {
			currentPage: currentPage,
			size: size,
			time: time,
		},
	});
}

export default {
	readStatistics,
	readTopFive,
	readDashboardWarning,
	readTopFiveDepart,
	readDepartmentStatus,
	readDepartmentDashboardWarning,
};
