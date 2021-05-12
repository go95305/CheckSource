package com.ssafy.checksource.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.checksource.model.dto.OpensourceDetailDTO;
import com.ssafy.checksource.model.dto.OpensourceListDTO;
import com.ssafy.checksource.model.dto.OpensourceSaveDTO;
import com.ssafy.checksource.model.dto.OpensourceUpdateDTO;
import com.ssafy.checksource.service.OpensourceService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/opensource")
@RequiredArgsConstructor
public class OpenSourceController {
	// 넣고
	// 이름 검색하고
	// 전체 목록 불러오고
	// 삭제? 하고

	private final OpensourceService opensourceService;

	@ApiOperation(value = "오픈소스 목록 불러오기")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "typeFilter", value = "타입필터 - Name/License", required = true, defaultValue = "Name"),
			@ApiImplicitParam(name = "keyword", value = "검색키워드 - 없으면 . 있으면 해당키워드값", defaultValue = ".", required = true),
			@ApiImplicitParam(name = "pageSize", value = "페이지당 보여줄 컨텐츠 갯수", defaultValue = "10", required = true),
			@ApiImplicitParam(name = "page", value = "기본-1 ex) 1-(1~10) 2-(11~20)", defaultValue = "1", required = true) })
	@GetMapping("/getList")
	public OpensourceListDTO getList(@RequestHeader("TOKEN") String token,@RequestParam String typeFilter, @RequestParam String keyword, @RequestParam int pageSize,
			@RequestParam int page) {

		return opensourceService.getOpensourceList(typeFilter, keyword, pageSize, page);

	}

	@ApiOperation(value = "오픈소스 하나의 상세정보 불러오기")
	@GetMapping("/getDetail/{opensourceId}")
	public OpensourceDetailDTO getDetail(@RequestHeader("TOKEN") String token,long opensourceId) {
		return opensourceService.getDetailOpensource(opensourceId);
	}

	@ApiOperation(value = "오픈소스 저장하기")
	@PostMapping("/addOpensource")
	public void insert(@RequestHeader("TOKEN") String token,@RequestBody OpensourceSaveDTO opsDto) {
		opensourceService.save(token,opsDto);
	}
	
	@ApiOperation(value = "오픈소스 업데이트하기")
	@PostMapping("/updateOpensource")
	public void update(@RequestHeader("TOKEN") String token,@RequestBody OpensourceUpdateDTO opsDto) {
		opensourceService.update(token,opsDto);
	}

}
