package com.ssafy.checksource.parser;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ssafy.checksource.model.dto.ParsingDTO;

public class PackageJsonParser {
	private List<ParsingDTO> depenList;
	
	private JsonParser jsonparser;
	
	@SuppressWarnings("deprecation")
	public PackageJsonParser(String json) {
		depenList = new ArrayList<ParsingDTO>();
		jsonparser = new JsonParser();
		parse(json);
		
	}
	
	private void parse(String json) {
		JsonObject jsonObject = (JsonObject) jsonparser.parse(json);
		JsonObject depenObject = (JsonObject) jsonObject.get("dependencies");
		
		for(String depenName : depenObject.keySet() ) {
			ParsingDTO depen = new ParsingDTO();
			depen.setGroupId("");
			depen.setArtifactId(depenName);
			depen.setVersion(depenObject.get(depenName).getAsString());
			depenList.add(depen);
		}
			
		
		
	}
//	JsonObject
	
	public List<ParsingDTO> getDepenList(){
		return depenList;
	}
	

}
