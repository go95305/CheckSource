package com.ssafy.checksource.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class AnalyUnmappedOpensouceListDTO {

	private List <UnmappendOpensourceDTO> unmappedList;
	private int totalPages;
}
