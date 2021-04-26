package com.ssafy.checksource.model.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Entity
@Data

public class User implements UserDetails{

	@Id
	@Column(name = "user_id")
	private Long userId;
	private String password;
	private String token;
	@Column(name = "gitlap_id")
	private String gitlapId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "depart_id", insertable=false, updatable=false)
	private Depart depart;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "job_id", insertable=false, updatable=false)
	private Job job;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
