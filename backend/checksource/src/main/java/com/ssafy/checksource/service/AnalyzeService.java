package com.ssafy.checksource.service;

import java.io.StringReader;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.InputSource;

import com.ssafy.checksource.model.dto.DependencyDTO;
import com.ssafy.checksource.parser.PomxmlSaxHandler;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AnalyzeService {

	// pom.xml Parsing
	// groupId, artifactId, version만 파싱하여 return
	public List<DependencyDTO> pomxmlParsing(String xml) throws Exception {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		
		SAXParser parser = factory.newSAXParser();
		PomxmlSaxHandler handler = new PomxmlSaxHandler();
		InputSource inputSource = new InputSource(new StringReader(xml));
		
		parser.parse(inputSource, handler);
		List<DependencyDTO> list = handler.getDepenList();
		
		for(DependencyDTO dto:list) {
			System.out.println(dto);
		}
		
		
		return list;
	}

}
