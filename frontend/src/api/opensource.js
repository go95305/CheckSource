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

export default { readOpenSourceList };
