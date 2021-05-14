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

function getLicenseName(licenseName) {
  return http.get(`/license/getNameList`, {
    params: {
      keyword: licenseName,
    },
  });
}

function addOpenSource(opensourceInfo) {
  return http.post(`/opensource/addOpensource`,opensourceInfo);
}

export default { readOpenSourceList, getLicenseName,addOpenSource };
function getDetailOpensource(opensourceId) {
    return http.get(`/opensource/getDetail/${opensourceId}`, {
        params: {
            opensourceId: opensourceId,
        },
    });
}

export default { readOpenSourceList, getDetailOpensource };
