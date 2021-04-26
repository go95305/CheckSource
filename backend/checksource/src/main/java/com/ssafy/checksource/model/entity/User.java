package com.ssafy.checksource.model.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "User")
public class User implements UserDetails{

	@Id
	@Column(name = "user_id")
	private String userId;
	private String userName;
	private String token;
    @Column(columnDefinition = "boolean default false")
	private boolean flag;
	@Column(name = "gitlap_id")
	private String gitlapId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "depart_id", insertable=false, updatable=false)
	private Depart depart;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "job_id", insertable=false, updatable=false)
	private Job job;

	
	@ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private final List<String> roles = new ArrayList<>();

    public List<String> getRoles() {
        roles.clear();
        roles.add("USER");
        return roles;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
    @Override
    public String getPassword() {
       return null;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public Boolean getFlag() {
        return this.flag;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public String getUsername() {
        return this.userName;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isEnabled() {
        return true;
    }

	
}
