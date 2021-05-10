package com.ssafy.checksource.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class OpensourceListDTO {
	List<OpensourceDTO> list;
	int totalPage;

}
