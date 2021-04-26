package com.ssafy.checksource.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Depart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "depart_id")
	private Long departId;
	private String name;
	
	@OneToMany(mappedBy = "depart")
    private List<User> user = new ArrayList<>();
}
