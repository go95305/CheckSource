package com.ssafy.checksource.model.key;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class OpensourceLicenseKey implements Serializable{
	@Column(name = "opensource_id")
	private Long opensourceId;
	
	@Column(name = "license_id")
	private Long licenseId;
}
