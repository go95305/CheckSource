package com.ssafy.checksource.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class LicenseListDTO {

	private List<LicenseDTO> licenseList;
	private int totalPage;
	
}

