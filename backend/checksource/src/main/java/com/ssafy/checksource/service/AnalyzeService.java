package com.ssafy.checksource.service;

import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.InputSource;

import com.ssafy.checksource.model.dto.LicenseDTO;
import com.ssafy.checksource.model.dto.OpensourceDTO;
import com.ssafy.checksource.model.dto.ParsingDTO;
import com.ssafy.checksource.model.entity.License;
import com.ssafy.checksource.model.entity.LicenseToOpensource;
import com.ssafy.checksource.model.entity.Opensource;
import com.ssafy.checksource.model.repository.LicenseRepository;
import com.ssafy.checksource.model.repository.LicenseToOpensourceRepository;
import com.ssafy.checksource.model.repository.OpensourceRepository;
import com.ssafy.checksource.parser.PomxmlSaxHandler;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AnalyzeService {
	
	private final ModelMapper modelMapper = new ModelMapper();
	
	private final OpensourceRepository opensourceRepository;
	private final LicenseToOpensourceRepository licenseToopensourceRepository;
	private final LicenseRepository licenseRepository;
	// packageManager = "pom.xml"
	// content = base64 encoding data
	public Object analyze(String packageManager, String content) throws Exception {
		Object list = null;
		byte[] decoded = Base64.getDecoder().decode(content);
		content = new String(decoded,StandardCharsets.UTF_8);
		if(packageManager.equals("pom.xml")) {
			list = pomXmlMatchingLicense(pomxmlParsing(content));
			//list = pomxmlParsing(content);
		}else {}
		
		return list;
	}
	
	// pom.xml Parsing
	// groupId, artifactId, version만 파싱하여 return
	public List<ParsingDTO> pomxmlParsing(String xml) throws Exception {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		
		SAXParser parser = factory.newSAXParser();
		PomxmlSaxHandler handler = new PomxmlSaxHandler();
		InputSource inputSource = new InputSource(new StringReader(xml));
		
		parser.parse(inputSource, handler);
		List<ParsingDTO> list = handler.getDepenList();		
		for(ParsingDTO i : list) {
			System.out.println(i);
		}
		
		return list;
	}
	
	//groupId, artifactId, version을 가지고 opensource 테이블에서 데이터를 받아옴
	public List<OpensourceDTO> pomXmlMatchingLicense(List<ParsingDTO> list){
		List<OpensourceDTO> opensourceList = new ArrayList<OpensourceDTO>();
		
		for(ParsingDTO dto : list) {
			Opensource ops = opensourceRepository.findByGroupIdAndArtifactId(dto.getGroupId(), dto.getArtifactId());
			OpensourceDTO opsDto = modelMapper.map(ops, OpensourceDTO.class);
			List<LicenseDTO> licenseList = new ArrayList<LicenseDTO>();
			for( LicenseToOpensource licenseopensource: licenseToopensourceRepository.findByLicense(ops.getOpensourceId())) { 
				License license = licenseRepository.findByLicenseId(licenseopensource.getLicense().getLicenseId());
				licenseList.add(modelMapper.map(license, LicenseDTO.class));
			}
			opsDto.setLicenseList(licenseList);
			opensourceList.add(opsDto);
		}
		
		
		return opensourceList;
	}

}
