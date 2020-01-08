package com.aisile.service;

import java.util.Collection;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AuthenticationProviderImpl implements AuthenticationProvider{

	private UserDetailsService userDetailsService;
	
	
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		//前端传来
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		
		UserDetails user = userDetailsService.loadUserByUsername(username);
		
		if(user ==null) {//证明没从数据库拿到东西
			System.out.println("222");
			throw new DisabledException("Wrong UserName.");
		}
		// 加密过程在这里体现
		System.out.println("结果CustomUserDetailsService后，已经查询出来的数据库存储密码:" + user.getPassword());
		// 密码加密
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if (!passwordEncoder.matches(password, user.getPassword())) {//页面传的密码和数据库查询的密码比较
			throw new DisabledException("Wrong password.");
		}
		
		 Collection<? extends GrantedAuthority> collection = user.getAuthorities();
		
		return new UsernamePasswordAuthenticationToken("qwe", "qwe", collection);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// 方法是否生效
		return true;
	}

}
