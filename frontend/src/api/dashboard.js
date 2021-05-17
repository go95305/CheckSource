import http from "./http.js";

//전체 통계 정보 불러오기
function readStatistics() {
	return http.get(`/dashboard/statistics`);
}

export default {
	readStatistics,
};
