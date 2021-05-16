package com.ssafy.checksource.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.checksource.model.dto.LicenseDetailDTO;
import com.ssafy.checksource.model.dto.LicenseIdDTO;
import com.ssafy.checksource.model.dto.LicenseListDTO;
import com.ssafy.checksource.model.dto.LicenseNameDTO;
import com.ssafy.checksource.model.dto.LicenseSaveDTO;
import com.ssafy.checksource.model.dto.LicenseUpdateDTO;
import com.ssafy.checksource.service.LicenseService;

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
	public LicenseListDTO getList(@RequestHeader("TOKEN") String token,@RequestParam String typeFilter, @RequestParam String keyword, @RequestParam int pageSize,
			@RequestParam int page) {
		
		return licenseService.getLicenseList(typeFilter, keyword, pageSize, page);
	}
	
	@GetMapping("/getNameList")
	public List<LicenseNameDTO> getNameList(@RequestHeader("TOKEN") String token, @RequestParam String keyword) {
		
		return licenseService.getLicenseNameList(keyword);
	}

	@ApiOperation(value = "라이선스 하나의 상세정보 불러오기")
	@GetMapping("/getDetail/{licenseId}")
	public LicenseDetailDTO getDetail(@RequestHeader("TOKEN") String token,long licenseId) {
		return licenseService.getDetailLicense(licenseId);
	}
	
	@ApiOperation(value = "라이선스 저장하기")
	@PostMapping("/addLicense")
	public void insert(@RequestHeader("TOKEN") String token,@RequestBody LicenseSaveDTO licDto) {
		licenseService.save(token,licDto);
	}
	
	@ApiOperation(value = "라이선스 업데이트하기")
	@PutMapping("/updateLicense")
	public void update(@RequestHeader("TOKEN") String token,@RequestBody LicenseUpdateDTO licDto) {
		licenseService.update(token,licDto);
	}
	
	@ApiOperation(value = "라이선스 삭제하기")
	@DeleteMapping("/deleteLicense")
	public void update(@RequestHeader("TOKEN") String token,@RequestBody LicenseIdDTO licenseId) {
		licenseService.delete(licenseId);
	}
}
