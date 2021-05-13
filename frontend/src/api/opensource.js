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

function addOpensource(opensourceInfo) {
  return http.get(`/opensource/addOpensource`, {
    params: {
      artifactId: opensourceInfo.artifactId,
      copyright: opensourceInfo.copyright,
      groupId: opensourceInfo.groupId,
      licenseId: opensourceInfo.licenseId,
      name: opensourceInfo.name,
      packageType: opensourceInfo.packagetype,
      url: opensourceInfo.url,
      version: opensourceInfo.version,
    },
  });
}

export default { readOpenSourceList, getLicenseName,addOpensource };
