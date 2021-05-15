package com.ssafy.checksource.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class AnalyOpensourceListDTO {
	
	private List<UnmappendOpensourceDTO> unmappedList;
	private List<OpensourceDTO> mappedList;
}
