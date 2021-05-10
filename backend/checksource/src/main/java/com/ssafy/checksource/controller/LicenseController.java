package com.ssafy.checksource.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.checksource.model.dto.LicenseDetailDTO;
import com.ssafy.checksource.model.dto.LicenseListDTO;
import com.ssafy.checksource.model.dto.OpensourceDetailDTO;
import com.ssafy.checksource.model.dto.OpensourceListDTO;
import com.ssafy.checksource.model.dto.OpensourcesaveDTO;
import com.ssafy.checksource.service.LicenseService;
import com.ssafy.checksource.service.OpensourceService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/license")
@RequiredArgsConstructor
public class LicenseController {

	private final LicenseService licenseService;

	@ApiOperation(value = "라이선스 목록 불러오기")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "typeFilter", value = "타입필터 - Name/Identifier", required = true, defaultValue = "Name"),
			@ApiImplicitParam(name = "keyword", value = "검색키워드 - 없으면 . 있으면 해당키워드값", defaultValue = ".", required = true),
			@ApiImplicitParam(name = "pageSize", value = "페이지당 보여줄 컨텐츠 갯수", defaultValue = "10", required = true),
			@ApiImplicitParam(name = "page", value = "기본-1 ex) 1-(1~10) 2-(11~20)", defaultValue = "1", required = true) })
	@GetMapping("/getList")
	public LicenseListDTO getList(@RequestParam String typeFilter, @RequestParam String keyword, @RequestParam int pageSize,
			@RequestParam int page) {
		System.out.println(keyword);
		return licenseService.getLicenseList(typeFilter, keyword, pageSize, page);

	}

	@ApiOperation(value = "라이선스 하나의 상세정보 불러오기")
	@GetMapping("/getDetail/{licenseId}")
	public LicenseDetailDTO getDetail(long licenseId) {
		return licenseService.getDetailLicense(licenseId);
	}
}
