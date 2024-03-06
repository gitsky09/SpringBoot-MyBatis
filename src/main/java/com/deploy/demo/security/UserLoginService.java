package com.deploy.demo.security;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.deploy.demo.core.dto.Permission;
import com.deploy.demo.entity.UserEntity;
import com.deploy.demo.repository.UserMapper;

@Service("userLoginService")
public class UserLoginService implements UserDetailsService{

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<UserEntity> user = userMapper.findByAccount(username);
		System.out.println(user.get().getAccount()+":"+user.get().getPassword());
		List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("admin");
		Permission tag = new Permission();
		
		if(user.isPresent()) {
			tag.setUsername(user.get().getAccount());
			tag.setPassword(user.get().getPassword());
			tag.setAuthorities(authorities);
		}else {
			throw new UsernameNotFoundException("User Not Found");
		}
	
		return tag;
	}

}
