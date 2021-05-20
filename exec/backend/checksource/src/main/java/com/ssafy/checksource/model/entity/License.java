package com.ssafy.checksource.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "license")
@NoArgsConstructor
@Data
public class License {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "license_id")
	private long licenseId;

	// 이름
	private String name;
	// 줄임말
	private String identifier;
	// 전문
	@Column(columnDefinition = "TEXT")
	private String contents;
	// url
	private String url;
	// osi승인여부
	private Boolean is_osi;
	// 복제, 배포, 수정의 권한 허용
	private Boolean is_modify;
	// 배포시 라이선스 사본 첨부
	private Boolean is_license;
	// 저작권 고지사항 또는 Attribution 고지사항 유지
	private Boolean is_copyright;
	// 배포시 소스코드 제공의무(Reciprocity)와 범위
	private String sourceopen;
	// 조합저작물(Lager Work) 작성 및 타 라이선스 배포 허용
	private Boolean is_anotherlicense;
	// 수정 시 수정내용 고지
	private Boolean is_modify_notice;
	// 보증의 부인
	private Boolean is_warranty;
	// 책임의 제한
	private Boolean is_respon;

	@OneToMany(mappedBy = "license", cascade = CascadeType.ALL)
	private List<LicenseOpensource> opensources = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
}
