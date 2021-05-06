package com.ssafy.checksource.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.checksource.model.dto.OpensourceDTO;
import com.ssafy.checksource.model.entity.Opensource;
import com.ssafy.checksource.model.repository.OpensourceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OpensourceService {
	private final OpensourceRepository opensourceRepository;
	private final ModelMapper modelMapper = new ModelMapper();

	public List<OpensourceDTO> getAllOpensource() {
		List<OpensourceDTO> list = new ArrayList<OpensourceDTO>();
		for (Opensource open : opensourceRepository.findAll()) {
			System.out.println(open);
			list.add(modelMapper.map(open, OpensourceDTO.class));
		}

		return list;
	}

}
