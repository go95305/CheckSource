package com.ssafy.checksource.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Login {

	@Id
	@Column(name = "user_id")
	private String userId;
	private String password;
}
