package com.deploy.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.deploy.demo.entity.AuthorityEntity;
import com.deploy.demo.repository.AuthorityMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorityService {

	final AuthorityMapper authorityMapper;

	@PostMapping("/authority")
	public Integer insertAuthority(AuthorityEntity authority) {
		return authorityMapper.insertAuthority(authority);
	}

	@GetMapping("/authority")
	public List<AuthorityEntity> listAuthority() {
		return authorityMapper.listAuthority();
	}

	@GetMapping("/authority/")
	public AuthorityEntity getAuthority(int id) {
		return authorityMapper.findByAuthorityId(id);
	}

	@PutMapping("/authority")
	public
	Integer updateAuthority(AuthorityEntity authority) {
		return authorityMapper.updateAuthority(authority);
	}

	@DeleteMapping("/authority/")
	public void deleteAuthority(int id) {
		authorityMapper.deleteAuthority(id);
	}
}