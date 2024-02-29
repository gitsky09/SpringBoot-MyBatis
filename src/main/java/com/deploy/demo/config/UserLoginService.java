package com.deploy.demo.config;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.deploy.demo.entity.UserEntity;
import com.deploy.demo.repository.UserMapper;

@Service("userLoginService")
public class UserLoginService implements UserDetailsService{

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<UserEntity> user = userMapper.findByAccount(username);
		if(user.isPresent()) {
			//查詢授權
		}else {
			throw new UsernameNotFoundException("User Not Found");
		}
		
		List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("normal");
		
		return new User(user.get().getAccount(), new BCryptPasswordEncoder().encode(user.get().getPassword()), true, true, true, true, authorities);
	}

}
