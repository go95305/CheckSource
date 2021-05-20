package com.ssafy.checksource.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class AnalyMappedOpensouceListDTO {

	private List<OpensourceDTO> mappedList;
	private int totalPages;
}
