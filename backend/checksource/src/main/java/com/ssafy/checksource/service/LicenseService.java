package com.ssafy.checksource.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.checksource.model.dto.LicenseDTO;
import com.ssafy.checksource.model.dto.OpensourceDTO;
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
//		saveEntity.set_commercial(licSave.get);
//		saveEntity.set_continue(is_continue);
//		saveEntity.set_copyright(is_copyright);
//		saveEntity.set_distribute(is_distribute);
//		saveEntity.set_license(is_license);
//		saveEntity.set_modify(is_modify);
//		saveEntity.set_osi(is_osi);
//		saveEntity.set_patent(is_patent);
//		saveEntity.set_private(is_private);
//		saveEntity.set_respon(is_respon);
//		saveEntity.set_sourceopen(is_sourceopen);
//		saveEntity.set_status(is_status);
//		saveEntity.set_warranty(is_warranty);
		License saveEntity = modelMapper.map(licSave, License.class);

		licenseRepository.save(saveEntity);
	}
}
