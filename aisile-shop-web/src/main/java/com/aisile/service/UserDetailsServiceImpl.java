package com.aisile.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.aisile.pojo.TbSeller;
import com.aisile.sellergoods.service.SellerService;

public class UserDetailsServiceImpl implements UserDetailsService {

	private SellerService sellerService;
	
	public void setSellerService(SellerService sellerService) {
		this.sellerService = sellerService;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
			List<GrantedAuthority> grantedAuths  = new ArrayList<GrantedAuthority>();
			grantedAuths .add(new SimpleGrantedAuthority("ROLE_SELLER"));
			System.out.println(">>>>>>>>>>>>>>>>>>");
			TbSeller seller = sellerService.findOne(username);
			System.out.println(seller.getPassword());
			if(seller!=null) {
				System.out.println("assad");
				if(seller.getStatus().equals("1")) {
					System.out.println("<<<<<<");
					return new User(username,seller.getPassword() , grantedAuths);
				}else {
					return null;
				}
			}else {
				return null;
			}
	}

}
