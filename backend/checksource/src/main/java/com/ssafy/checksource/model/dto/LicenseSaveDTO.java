package com.ssafy.checksource.model.dto;

import javax.persistence.Column;

import lombok.Data;

@Data
public class LicenseSaveDTO {
	
	//이름
	private String name;
	//줄임말
	private String identifier;
	//전문
	private String contents;
	//url
	private String url;
	//osi승인여부
	private boolean is_osi;
	//복제, 배포, 수정의 권한 허용
	private boolean is_modify;
	//배포시 라이선스 사본 첨부
	private boolean is_license;
	//저작권 고지사항 또는 Attribution 고지사항 유지
	private boolean is_copyright;
	//배포시 소스코드 제공의무(Reciprocity)와 범위
	private String sourceopen;
	//조합저작물(Lager Work) 작성 및 타 라이선스 배포 허용
	private boolean is_anotherlicense;
	//수정 시 수정내용 고지
	private boolean is_modify_notice;
	//보증의 부인
	private boolean is_warranty;
	//책임의 제한
	private boolean is_respon;
	//상업적 사용 가능
	private boolean is_commercial;
	//동일한 라이선스 유지
	private boolean is_continue;
	//특허권 사용 여부
	private boolean is_patent;
	//비공개 사용 여부
	private boolean is_private;

}
