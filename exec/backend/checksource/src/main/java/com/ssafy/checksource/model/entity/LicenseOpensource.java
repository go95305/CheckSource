package com.ssafy.checksource.model.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ssafy.checksource.model.key.OpensourceLicenseKey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "LICENSE_OPENSOURCE")
public class LicenseOpensource {

    @EmbeddedId
    @Column(name = "opslic_id", unique = true)
    private OpensourceLicenseKey opslic_id = new OpensourceLicenseKey();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "opensource_id",insertable=false, updatable=false)
	private Opensource opensource;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "license_id",insertable=false, updatable=false)
	private License license;

	@Override
	public String toString() {
		return "";
	}
	
	
}
