package com.ssafy.checksource.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "licensetoopensource")
@NoArgsConstructor
@Data
public class LicenseToOpensource {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "opensource_id")
	private Opensource opensource;

	@ManyToOne
	@JoinColumn(name = "license_id")
	private License license;

	@Override
	public String toString() {
		return "";
	}
	
	
}
