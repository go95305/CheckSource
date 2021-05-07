package com.ssafy.checksource.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.checksource.model.dto.LicenseDTO;
import com.ssafy.checksource.model.dto.OpensourceDTO;
import com.ssafy.checksource.model.dto.OpensourcesaveDTO;
import com.ssafy.checksource.model.entity.License;
import com.ssafy.checksource.model.entity.LicenseToOpensource;
import com.ssafy.checksource.model.entity.Opensource;
import com.ssafy.checksource.model.repository.OpensourceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OpensourceService {
	private final OpensourceRepository opensourceRepository;
	private final ModelMapper modelMapper = new ModelMapper();

	public List<OpensourceDTO> getAllOpensource() {
		List<OpensourceDTO> list = new ArrayList<OpensourceDTO>();
		for (Opensource ops : opensourceRepository.findAll()) {
			OpensourceDTO opsDto = modelMapper.map(ops, OpensourceDTO.class);
			List<LicenseDTO> licenseList = new ArrayList<LicenseDTO>();
			for (LicenseToOpensource licenseopensource : ops.getLicenses()) {
				License license = licenseopensource.getLicense();
				licenseList.add(modelMapper.map(license, LicenseDTO.class));
			}
			opsDto.setLicenseList(licenseList);
			
			list.add(opsDto);
		}

		return list;
	}
	
	public OpensourceDTO getDetailOpensource(long id) {
		Opensource ops = opensourceRepository.findById(id);
		OpensourceDTO opsDto = modelMapper.map(ops, OpensourceDTO.class);
		return opsDto;
	}
//	private String name;
//	private String url;
//	private String copyright;
//	private String version;
//	private String packageType;
//	private String groupId;
//	private String artifactId;
//	private List<Long> licenseId;
	public void save(OpensourcesaveDTO ossSave) {
		Opensource saveEntity = new Opensource();
		saveEntity.setArtifactId(ossSave.getArtifactId());
		saveEntity.setCopyright(ossSave.getCopyright());
		saveEntity.setGroupId(ossSave.getGroupId());
		saveEntity.setName(ossSave.getName());
		saveEntity.setPackageType(ossSave.getPackageType());
		saveEntity.setUrl(ossSave.getUrl());
		saveEntity.setVersion(ossSave.getVersion());
		
		opensourceRepository.save(saveEntity);
		
		
	}
	
}
