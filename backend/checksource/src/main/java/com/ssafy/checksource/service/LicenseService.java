package com.ssafy.checksource.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.checksource.model.dto.LicenseDTO;
import com.ssafy.checksource.model.dto.OpensourceDetailDTO;
import com.ssafy.checksource.model.dto.OpensourcesaveDTO;
import com.ssafy.checksource.model.entity.License;
import com.ssafy.checksource.model.entity.Opensource;
import com.ssafy.checksource.model.repository.LicenseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class LicenseService {

	private final LicenseRepository licenseRepository;
	private final ModelMapper modelMapper = new ModelMapper();
	
	public LicenseDTO getDetailLicense(long id) {
		License lic = licenseRepository.findByLicenseId(id);
		
		LicenseDTO licDto = modelMapper.map(lic, LicenseDTO.class);
		return licDto;
	}
	
	public void save(LicenseDTO licSave) {
		License saveEntity = modelMapper.map(licSave, License.class);
		licenseRepository.save(saveEntity);
	}
}
