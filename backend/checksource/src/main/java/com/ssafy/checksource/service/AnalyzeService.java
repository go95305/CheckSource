package com.ssafy.checksource.service;

import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.InputSource;

import com.ssafy.checksource.model.dto.PomXmlDepenDTO;
import com.ssafy.checksource.parser.PomxmlSaxHandler;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AnalyzeService {

	
	// packageManager = "pom.xml"
	// content = base64 encoding data
	public Object analyze(String packageManager, String content) throws Exception {
		Object list = null;
		byte[] decoded = Base64.getDecoder().decode(content);
		content = new String(decoded,StandardCharsets.UTF_8);
		if(packageManager.equals("pom.xml")) {
			//list = pomXmlMatchingLicense(pomxmlParsing(content));
			list = pomxmlParsing(content);
		}else {}
		
		return list;
	}
	
	// pom.xml Parsing
	// groupId, artifactId, version만 파싱하여 return
	public List<PomXmlDepenDTO> pomxmlParsing(String xml) throws Exception {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		
		SAXParser parser = factory.newSAXParser();
		PomxmlSaxHandler handler = new PomxmlSaxHandler();
		InputSource inputSource = new InputSource(new StringReader(xml));
		
		parser.parse(inputSource, handler);
		List<PomXmlDepenDTO> list = handler.getDepenList();		
		for(PomXmlDepenDTO i : list) {
			System.out.println(i);
		}
		
		return list;
	}
	
	//groupId, artifactId, version을 가지고 opensource 테이블에서 데이터를 받아옴
	public List<PomXmlDepenDTO> pomXmlMatchingLicense(List<PomXmlDepenDTO> list){
		
		
		
		
		return null;
	}

}
