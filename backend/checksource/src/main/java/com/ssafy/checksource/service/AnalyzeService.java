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
import com.ssafy.checksource.model.entity.LicenseOpensource;
import com.ssafy.checksource.model.entity.Opensource;
import com.ssafy.checksource.model.repository.LicenseOpensourceRepository;
import com.ssafy.checksource.model.repository.LicenseRepository;
import com.ssafy.checksource.model.repository.OpensourceRepository;
import com.ssafy.checksource.parser.PomxmlSaxHandler;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AnalyzeService {

	private final ModelMapper modelMapper = new ModelMapper();

	private final OpensourceRepository opensourceRepository;
	private final LicenseOpensourceRepository licenseopensourceRepository;
	private final LicenseRepository licenseRepository;

	// packageManager = "pom.xml"
	// content = base64 encoding data
	public Object analyze(int projectId,String fileName, String content,String filePath) throws Exception {
		Object list = null;
		byte[] decoded = Base64.getDecoder().decode(content);
		content = new String(decoded, StandardCharsets.UTF_8);
		if (fileName.equals("pom.xml")) {
			list = getOpensourceId(pomxmlParsing(content));
			
		} else {
		}
		//insert 치세오
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
		for (ParsingDTO i : list) {
			System.out.println(i);
		}

		return list;
	}
	
	// opensourceId 찾아오기
	public List<Long> getOpensourceId(List<ParsingDTO> list){
		List<Long> opensourceList = new ArrayList<Long>();
		for (ParsingDTO dto : list) {
			Opensource ops = opensourceRepository.findByGroupIdAndArtifactId(dto.getGroupId(), dto.getArtifactId());
			OpensourceDTO opsDto;
			if (ops != null) {
				opensourceList.add(ops.getOpensourceId());
			}else { //db에 오픈소스 없는 경우
				
			}
		}
		return opensourceList;
	}
	// groupId, artifactId, version을 가지고 opensource 테이블에서 데이터를 받아옴
	public List<OpensourceDTO> pomXmlMatchingLicense(List<ParsingDTO> list) {
		List<OpensourceDTO> opensourceList = new ArrayList<OpensourceDTO>();

		for (ParsingDTO dto : list) {
			Opensource ops = opensourceRepository.findByGroupIdAndArtifactId(dto.getGroupId(), dto.getArtifactId());
			OpensourceDTO opsDto;
			if (ops != null) {
				opsDto = modelMapper.map(ops, OpensourceDTO.class);
				List<LicenseDTO> licenseList = new ArrayList<LicenseDTO>();
				for (LicenseOpensource licenseopensource : ops.getLicenses()) {
					License license = licenseopensource.getLicense();
					licenseList.add(modelMapper.map(license, LicenseDTO.class));
				}
				opsDto.setLicenseList(licenseList);
			}else {
				opsDto = new OpensourceDTO();
				opsDto.setGroupId(dto.getGroupId());
				opsDto.setArtifactId(dto.getArtifactId());
			}
			opensourceList.add(opsDto);
		}

		return opensourceList;
	}

}
