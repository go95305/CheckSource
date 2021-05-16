package com.ssafy.checksource.service;


import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.checksource.config.security.JwtTokenProvider;
import com.ssafy.checksource.model.dto.LicenseDetailDTO;
import com.ssafy.checksource.model.dto.OpensourceDTO;
import com.ssafy.checksource.model.dto.OpensourceDetailDTO;
import com.ssafy.checksource.model.dto.OpensourceIdDTO;
import com.ssafy.checksource.model.dto.OpensourceListDTO;
import com.ssafy.checksource.model.dto.OpensourceSaveDTO;
import com.ssafy.checksource.model.dto.OpensourceUpdateDTO;
import com.ssafy.checksource.model.entity.License;
import com.ssafy.checksource.model.entity.LicenseOpensource;
import com.ssafy.checksource.model.entity.Opensource;
import com.ssafy.checksource.model.entity.User;
import com.ssafy.checksource.model.key.OpensourceLicenseKey;
import com.ssafy.checksource.model.repository.LicenseOpensourceRepository;
import com.ssafy.checksource.model.repository.OpensourceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OpensourceService {

	private final OpensourceRepository opensourceRepository;
	private final ModelMapper modelMapper = new ModelMapper();
	private final LicenseOpensourceRepository licenseopensourceRepository;
	private final JwtTokenProvider jwtTokenProvider;

	public OpensourceListDTO getOpensourceList(String typeFilter, String keyword, int pageSize, int page) {
		OpensourceListDTO opsListDto = new OpensourceListDTO();
		List<OpensourceDTO> opensourceList = new ArrayList<OpensourceDTO>();
		Page<Opensource> opensourcePagedata = null;
		Pageable paging = PageRequest.of(page-1, pageSize,Direction.ASC,"name");
		
		if(keyword.equals(".") || keyword.equals("")) {
			opensourcePagedata = opensourceRepository.findAll(paging);
		}else if(typeFilter.equals("Name")) {
			opensourcePagedata = opensourceRepository.findByNameLike("%"+keyword+"%",paging);
		}else if(typeFilter.equals("License")) {
			opensourcePagedata = opensourceRepository.findByLicenseLike("%"+keyword+"%", paging);
		}
		
		for (Opensource ops : opensourcePagedata) {
			
			OpensourceDTO opsDto = modelMapper.map(ops, OpensourceDTO.class);
			List<String> licenseNameList = new ArrayList<String>();
			for (LicenseOpensource licenseopensource : ops.getLicenses()) {
				License license = licenseopensource.getLicense();
				licenseNameList.add(license.getName());
			}
			opsDto.setLicenseNameList(licenseNameList);
			opsDto.setUserId((ops.getUser().getUserId()));
			opensourceList.add(opsDto);
		}
		opsListDto.setTotalPage(opensourcePagedata.getTotalPages());
		opsListDto.setList(opensourceList);
		return opsListDto;
	}

	public OpensourceDetailDTO getDetailOpensource(long id) {
		Opensource ops = opensourceRepository.findById(id);
		OpensourceDetailDTO opsDto = modelMapper.map(ops, OpensourceDetailDTO.class);
		opsDto.setUserName(ops.getUser().getName());
		List<LicenseDetailDTO> licenseList = new ArrayList<LicenseDetailDTO>();
		for (LicenseOpensource licenseopensource : ops.getLicenses()) {
			License license = licenseopensource.getLicense();
			licenseList.add(modelMapper.map(license, LicenseDetailDTO.class));
		}
		opsDto.setLicenseList(licenseList);
		return opsDto;
	}

	public void save(String token, OpensourceSaveDTO ossSave) {
		Opensource saveEntity = new Opensource();
		saveEntity.setArtifactId(ossSave.getArtifactId());
		saveEntity.setCopyright(ossSave.getCopyright());
		saveEntity.setGroupId(ossSave.getGroupId());
		saveEntity.setName(ossSave.getName());
		saveEntity.setPackageType(ossSave.getPackageType());
		saveEntity.setUrl(ossSave.getUrl());
		saveEntity.setVersion(ossSave.getVersion());
		
		String userId = jwtTokenProvider.getUserId(token);
		User saveuser = new User();
		saveuser.setUserId(userId);
		saveEntity.setUser(saveuser);
		Opensource ops = opensourceRepository.save(saveEntity);

		long opsId = ops.getOpensourceId();
		for (long licenseId : ossSave.getLicenseId()) {
			LicenseOpensource licops = new LicenseOpensource();
			OpensourceLicenseKey opslickey = new OpensourceLicenseKey();
			opslickey.setOpensourceId(opsId);
			opslickey.setLicenseId(licenseId);
			licops.setOpslic_id(opslickey);
			licenseopensourceRepository.save(licops);
		}
	}
	
	public void update(String token, OpensourceUpdateDTO ossUpdate) {
		Opensource saveEntity = new Opensource();
		saveEntity.setOpensourceId(ossUpdate.getOpensourceId());
		saveEntity.setArtifactId(ossUpdate.getArtifactId());
		saveEntity.setCopyright(ossUpdate.getCopyright());
		saveEntity.setGroupId(ossUpdate.getGroupId());
		saveEntity.setName(ossUpdate.getName());
		saveEntity.setPackageType(ossUpdate.getPackageType());
		saveEntity.setUrl(ossUpdate.getUrl());
		saveEntity.setVersion(ossUpdate.getVersion());
		
		String userId = jwtTokenProvider.getUserId(token);
		User saveuser = new User();
		saveuser.setUserId(userId);
		saveEntity.setUser(saveuser);
		Opensource ops = opensourceRepository.save(saveEntity);

		long opsId = ops.getOpensourceId();
		for (long licenseId : ossUpdate.getLicenseId()) {
			LicenseOpensource licops = new LicenseOpensource();
			OpensourceLicenseKey opslickey = new OpensourceLicenseKey();
			opslickey.setOpensourceId(opsId);
			opslickey.setLicenseId(licenseId);
			licops.setOpslic_id(opslickey);
			licenseopensourceRepository.save(licops);
		}
	}
	
	public void delete(OpensourceIdDTO opensourceId) {
		opensourceRepository.deleteById(opensourceId.getOpensourceId());
	}

}
