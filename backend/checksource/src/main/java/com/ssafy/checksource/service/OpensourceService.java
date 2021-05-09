package com.ssafy.checksource.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.checksource.model.dto.OpensourceDTO;
import com.ssafy.checksource.model.dto.OpensourcelistDTO;
import com.ssafy.checksource.model.dto.OpensourcesaveDTO;
import com.ssafy.checksource.model.entity.License;
import com.ssafy.checksource.model.entity.LicenseOpensource;
import com.ssafy.checksource.model.entity.Opensource;
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
	private final LicenseOpensourceRepository licenseToopensourceRepository;

	public List<OpensourcelistDTO> getOpensourceList(String typeFilter, String keyword, int pageSize, int page) {
		List<OpensourcelistDTO> list = new ArrayList<OpensourcelistDTO>();
		Page<Opensource> Opensourcelist = null;
		if(typeFilter.equals("전체")) {
			Opensourcelist = opensourceRepository.findAll(PageRequest.of(page-1, pageSize,Direction.ASC,"name"));
		}
		System.out.println("---------------------");
		System.out.println(Opensourcelist.getTotalPages());
		for (Opensource ops : Opensourcelist) {
			OpensourcelistDTO opsDto = modelMapper.map(ops, OpensourcelistDTO.class);
			List<String> licenseNameList = new ArrayList<String>();
			for (LicenseOpensource licenseopensource : ops.getLicenses()) {
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
		for (long licenseId : ossSave.getLicenseId()) {
			LicenseOpensource licops = new LicenseOpensource();
			OpensourceLicenseKey opslickey = new OpensourceLicenseKey();
			opslickey.setOpensourceId(opsId);
			opslickey.setLicenseId(licenseId);
			licops.setOpslic_id(opslickey);
			licenseToopensourceRepository.save(licops);
		}

	}

}
