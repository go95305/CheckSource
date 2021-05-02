package com.ssafy.checksource.parser;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.ssafy.checksource.model.dto.DependencyDTO;

public class PomxmlSaxHandler extends DefaultHandler{
	private List<DependencyDTO> depenList;
	
	private DependencyDTO depen;
	
	private String str;
	
	public PomxmlSaxHandler() {
		depenList = new ArrayList<DependencyDTO>();
	}
	
	@Override
	public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
		
		if(name.equals("dependency")) {
			depen = new DependencyDTO();
		}
		
	}
	
	@Override
	public void endElement(String uri, String localName, String name) throws SAXException {
		if(name.equals("dependency")) {
			depenList.add(depen);
		}
		
		if(name.equals("groupId")) {
			depen.setGroupId(str);
		}
		if(name.equals("artifactId")) {
			depen.setArtifactId(str);
		}
		if(name.equals("version")) {
			depen.setVersion(str);
		}
	}
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		str = new String(ch,start,length);
	}
	
}
