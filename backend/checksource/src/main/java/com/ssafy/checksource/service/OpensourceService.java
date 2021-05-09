package com.ssafy.checksource.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.checksource.model.dto.LicenseDTO;
import com.ssafy.checksource.model.dto.OpensourceDTO;
import com.ssafy.checksource.model.dto.OpensourcelistDTO;
import com.ssafy.checksource.model.dto.OpensourcesaveDTO;
import com.ssafy.checksource.model.entity.License;
import com.ssafy.checksource.model.entity.LicenseToOpensource;
import com.ssafy.checksource.model.entity.Opensource;
import com.ssafy.checksource.model.key.OpensourceLicenseKey;
import com.ssafy.checksource.model.repository.LicenseToOpensourceRepository;
import com.ssafy.checksource.model.repository.OpensourceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OpensourceService {
	
	private final OpensourceRepository opensourceRepository;
	private final ModelMapper modelMapper = new ModelMapper();
	private final LicenseToOpensourceRepository licenseToopensourceRepository;
	
	public List<OpensourcelistDTO> getAllOpensource() {
		List<OpensourcelistDTO> list = new ArrayList<OpensourcelistDTO>();
		for (Opensource ops : opensourceRepository.findAll()) {
			OpensourcelistDTO opsDto = modelMapper.map(ops, OpensourcelistDTO.class);
			List<String> licenseNameList = new ArrayList<String>();
			for (LicenseToOpensource licenseopensource : ops.getLicenses()) {
				License license = licenseopensource.getLicense();
				licenseNameList.add(license.getName());
			}
			opsDto.setLicenseNameList(licenseNameList);
			
			list.add(opsDto);
		}

		return list;
	}
	
	public OpensourceDTO getDetailOpensource(long id) {
		Opensource ops = opensourceRepository.findById(id);
		OpensourceDTO opsDto = modelMapper.map(ops, OpensourceDTO.class);
		return opsDto;
	}

	public void save(OpensourcesaveDTO ossSave) {
		Opensource saveEntity = new Opensource();
		saveEntity.setArtifactId(ossSave.getArtifactId());
		saveEntity.setCopyright(ossSave.getCopyright());
		saveEntity.setGroupId(ossSave.getGroupId());
		saveEntity.setName(ossSave.getName());
		saveEntity.setPackageType(ossSave.getPackageType());
		saveEntity.setUrl(ossSave.getUrl());
		saveEntity.setVersion(ossSave.getVersion());
		
		Opensource ops = opensourceRepository.save(saveEntity);
		
		long opsId = ops.getOpensourceId();
		for(long licenseId : ossSave.getLicenseId() ) {
			LicenseToOpensource licops = new LicenseToOpensource();
			OpensourceLicenseKey opslickey = new OpensourceLicenseKey();
			opslickey.setOpensourceId(opsId);
			opslickey.setLicenseId(licenseId);
			licops.setOpslic_id(opslickey);
			licenseToopensourceRepository.save(licops);
		}
		
		
		
		
	}
	
}
