import http from "./http.js";

//전체 통계 정보 불러오기
function readStatistics() {
	return http.get(`/dashboard/statistics`);
}

//전체 통계 정보 불러오기
function readTopFive() {
	return http.get(`/dashboard/top5`);
}

//부서별 통계 정보 불러오기
function readTopFiveDepart(departId) {
	return http.get(`/dashboard/top5/${departId}`);
}

export default {
	readStatistics,
	readTopFive,
	readTopFiveDepart,
};
