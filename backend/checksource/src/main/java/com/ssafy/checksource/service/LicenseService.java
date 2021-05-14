package com.ssafy.checksource.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.checksource.config.security.JwtTokenProvider;
import com.ssafy.checksource.model.dto.LicenseDTO;
import com.ssafy.checksource.model.dto.LicenseDetailDTO;
import com.ssafy.checksource.model.dto.LicenseListDTO;
import com.ssafy.checksource.model.dto.LicenseNameDTO;
import com.ssafy.checksource.model.dto.LicenseSaveDTO;
import com.ssafy.checksource.model.dto.LicenseUpdateDTO;
import com.ssafy.checksource.model.entity.License;
import com.ssafy.checksource.model.entity.User;
import com.ssafy.checksource.model.repository.LicenseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class LicenseService {

	private final LicenseRepository licenseRepository;
	private final ModelMapper modelMapper = new ModelMapper();
	private final JwtTokenProvider jwtTokenProvider;
	
	public LicenseDetailDTO getDetailLicense(long id) {
		License lic = licenseRepository.findByLicenseId(id);
		
		LicenseDetailDTO licDto = modelMapper.map(lic, LicenseDetailDTO.class);
		return licDto;
	}
	public LicenseListDTO getLicenseList(String typeFilter, String keyword, int pageSize, int page) {
		LicenseListDTO licenseListDTO = new LicenseListDTO();
		List<LicenseDTO> licenseList = new ArrayList<LicenseDTO>();
		Page<License> licensePagedata = null;
		Pageable paging = PageRequest.of(page-1, pageSize,Direction.ASC,"name");
		
		if(keyword.equals(".")|| keyword.equals("")) {
			licensePagedata = licenseRepository.findAll(paging);
		}else if(typeFilter.equals("Name")) {
			licensePagedata = licenseRepository.findByNameLike("%"+keyword+"%",paging);
		}else if(typeFilter.equals("Identifier")) {
			licensePagedata = licenseRepository.findByIdentifierLike("%"+keyword+"%",paging);
		}
		
		for (License lic : licensePagedata) {
			LicenseDTO licDto = modelMapper.map(lic, LicenseDTO.class);
			licenseList.add(licDto);
		}
		licenseListDTO.setTotalPage(licensePagedata.getTotalPages());
		licenseListDTO.setLicenseList(licenseList);
		return licenseListDTO;
	}
	
	public List<LicenseNameDTO> getLicenseNameList(String keyword) {
		List<LicenseNameDTO> licenseNameList = licenseRepository.findByNameLike("%"+keyword+"%").stream().map(LicenseNameDTO::new)
				.collect(Collectors.toList());
		return licenseNameList;
	}

	public void save(String token,LicenseSaveDTO licSave) {
		License saveEntity = modelMapper.map(licSave, License.class);
		String userId = jwtTokenProvider.getUserId(token);
		User saveuser = new User();
		saveuser.setUserId(userId);
		saveEntity.setUser(saveuser);
		licenseRepository.save(saveEntity);
	}
	
	public void update(String token,LicenseUpdateDTO licUpdate) {
		License updateEntity = modelMapper.map(licUpdate, License.class);
		String userId = jwtTokenProvider.getUserId(token);
		User saveuser = new User();
		saveuser.setUserId(userId);
		updateEntity.setUser(saveuser);
		licenseRepository.save(updateEntity);
	}
	public void delete(long licenseId) {
		licenseRepository.deleteById(licenseId);
	}
}
