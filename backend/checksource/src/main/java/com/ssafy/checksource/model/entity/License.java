package com.ssafy.checksource.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "license_id")
	private long licenseId;
	
	private String name;
	private String identifier;
	@Column(columnDefinition = "TEXT")
	private String contents;
	private String url;
	private boolean is_osi;
	private boolean is_copyright;
	private boolean is_license;
	private boolean is_commercial;
	private boolean is_distribute;
	private boolean is_modify;
	private boolean is_private;
	private boolean is_patent;
	private boolean is_respon;
	private boolean is_warranty;
	private boolean is_sourceopen;
	private boolean is_continue;
	private boolean is_status;
	
	@OneToMany(mappedBy = "license", cascade = CascadeType.ALL)
    private List<LicenseOpensource> opensources = new ArrayList<>();

}
